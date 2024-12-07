document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var selectedNurse = null;

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        initialDate: '2024-12-01',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        events: function(fetchInfo, successCallback, failureCallback) {
            const url = selectedNurse
                ? `/api/schedule?nurseName=${encodeURIComponent(selectedNurse)}`
                : '/api/schedule';

            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        // 서버 오류 처리
                        throw new Error(`서버 오류: ${response.status} ${response.statusText}`);
                    }
                    return response.json();
                })
                .then(data => {
                    console.log("Received data:", data); // 디버깅용 로그

                    // 데이터가 배열인지 확인하고 처리
                    if (Array.isArray(data)) {
                        var events = data.map(function(schedule) {
                            const nurseName = schedule.nurse ? schedule.nurse.name : '근무자 없음';

                            // 근무 유형과 날짜를 로그에 출력
                            console.log(`근무하는 사람: ${nurseName} - 근무 유형: ${schedule.workType} - 날짜: ${schedule.start}`);

                            if (!schedule.start) return null;  // start 값이 없으면 처리하지 않음

                            let shift = schedule.workType && schedule.workType.trim() !== '' ? schedule.workType : '미정';
                            let colors = getShiftColors(shift);

                            return {
                                title: nurseName,
                                start: schedule.start,
                                end: schedule.end || schedule.start,  // end 값이 없으면 start로 설정
                                backgroundColor: colors.backgroundColor,
                                borderColor: colors.borderColor
                            };
                        }).filter(event => event !== null);

                        successCallback(events);
                    } else {
                        console.error("배열 형식을 예상했으나 받은 데이터:", data);
                        alert("서버에서 예상한 형식의 데이터를 받지 못했습니다.");
                        failureCallback(new Error("Invalid data format"));
                    }
                })
                .catch(error => {
                    console.error("Error fetching schedule:", error);
                    alert("스케줄 데이터를 불러오는 중 오류가 발생했습니다. 관리자에게 문의하세요.");
                    failureCallback(error);
                });
        },
        eventClick: function(info) {
            // 클릭된 이벤트의 세부 정보 표시
            alert(`근무자: ${info.event.title}\n근무 시작: ${info.event.start.toLocaleString()}` +
                  (info.event.end ? `\n근무 종료: ${info.event.end.toLocaleString()}` : ''));
        },
        displayEventTime: true // 이벤트 시간 표시
    });

    calendar.render();

    document.getElementById('filterButton').addEventListener('click', function () {
        selectedNurse = prompt("간호사 이름을 입력하세요:");
        if (selectedNurse && selectedNurse.trim() !== '') {
            calendar.refetchEvents(); // 간호사 이름이 입력되면 일정 새로고침
        } else {
            alert("간호사 이름을 입력해 주세요.");
        }
    });

    function getShiftColors(shift) {
        const shiftColors = {
            "데이": { backgroundColor: '#90EE90', borderColor: '#008000' },
            "이브닝": { backgroundColor: '#FFD700', borderColor: '#FFD700' },
            "나이트": { backgroundColor: '#ADD8E6', borderColor: '#4682B4' },
            "미정": { backgroundColor: '#D3D3D3', borderColor: '#808080' }
        };
        return shiftColors[shift] || shiftColors["미정"];
    }
});
