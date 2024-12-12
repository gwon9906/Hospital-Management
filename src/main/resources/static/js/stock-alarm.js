document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('alarmModal');
    const btn = document.getElementById('showAlarms');
    const span = document.getElementsByClassName('close')[0];
    const alarmList = document.getElementById('alarmList');
    const alarmCount = document.getElementById('alarmCount');

    // 알람 내역 조회 함수
    async function fetchAlarms() {
        try {
            const response = await fetch('/api/alarms');
            const alarms = await response.json();

            // 알람 카운트 업데이트
            alarmCount.textContent = alarms.length;

            // 알람 리스트 초기화
            alarmList.innerHTML = '';

            // 알람 내역 표시
            alarms.forEach(alarm => {
                const alarmDiv = document.createElement('div');
                alarmDiv.className = 'alarm-item';
                alarmDiv.innerHTML = `
                    <div>${alarm.message}</div>
                    <div class="alarm-date">
                        ${new Date(alarm.createdDate).toLocaleString()}
                    </div>
                `;
                alarmList.appendChild(alarmDiv);
            });
        } catch (error) {
            console.error('알람 조회 중 오류 발생:', error);
        }
    }

    // 초기 알람 내역 조회
    fetchAlarms();

    // 30초마다 알람 내역 갱신
    setInterval(fetchAlarms, 30000);

    // 모달 열기
    btn.onclick = function() {
        modal.style.display = "block";
        fetchAlarms(); // 모달 열 때 최신 데이터 조회
    }

    // 모달 닫기
    span.onclick = function() {
        modal.style.display = "none";
    }

    // 모달 외부 클릭 시 닫기
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});