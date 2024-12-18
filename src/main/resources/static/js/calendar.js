document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        events: function(fetchInfo, successCallback, failureCallback) {
            var nurseName = localStorage.getItem('selectedNurse');  // 로컬 스토리지에서 간호사 이름 가져오기

            var url = '/schedules/api/schedule';
            if (nurseName) {
                url += '?nurseName=' + encodeURIComponent(nurseName);  // 간호사 이름이 있으면 쿼리 파라미터로 추가
            }

            fetch(url)
                .then(response => response.json())
                .then(data => {
                    console.log("Received events:", data); // 데이터 확인
                    var events = data.map(function(schedule) {
                        return {
                            title: schedule.title,  // title이 근무자 이름과 근무유형
                            start: schedule.start,
                            end: schedule.end || schedule.start  // end가 없으면 start와 동일
                        };
                    });

                    successCallback(events);  // FullCalendar에 이벤트 전달
                })
                .catch(error => {
                    console.error("Error fetching events:", error);
                    failureCallback(error);
                });
        },
        eventClick: function(info) {
            // 클릭 시 이벤트 세부정보 표시
            alert(`근무자: ${info.event.title}\n`);
        },
        displayEventTime: false  // 시간 표시하지 않음
    });

    calendar.render();

    document.getElementById('filterButton').addEventListener('click', function () {
        var selectedNurse = prompt("간호사 이름을 입력하세요:");
        if (selectedNurse && selectedNurse.trim() !== '') {
            localStorage.setItem('selectedNurse', selectedNurse.trim());  // 간호사 이름을 로컬 스토리지에 저장
            calendar.refetchEvents();  // 간호사 이름이 입력되면 일정 새로고침
        } else {
            alert("간호사 이름을 입력해 주세요.");
        }
    });

    // 새로고침 시 로컬 스토리지에서 간호사 이름을 가져와서 처리
    window.addEventListener('beforeunload', function() {
        localStorage.removeItem('selectedNurse');  // 새로고침 시 로컬 스토리지에서 간호사 이름을 제거
    });
});
