// script.js
document.addEventListener("DOMContentLoaded", () => {
    console.log("병원 관리 시스템 메인 화면 로드 완료.");
});
document.addEventListener("DOMContentLoaded", () => {
    // 가상의 재고 데이터 (백엔드에서 가져올 데이터)
    const stockData = [
        { name: "타이레놀", quantity: 5, expiry: "2024-12-31", location: "A1" },
        { name: "아스피린", quantity: 15, expiry: "2025-01-15", location: "B2" },
        { name: "인슐린", quantity: 2, expiry: "2024-08-30", location: "C1" },
    ];

    // 테이블에 데이터 추가
    const tableBody = document.getElementById("stockTable").querySelector("tbody");
    stockData.forEach(stock => {
        const row = document.createElement("tr");
        if (stock.quantity < 10) {
            row.classList.add("low-stock"); // 부족한 재고 강조
        }
        row.innerHTML = `
            <td>${stock.name}</td>
            <td>${stock.quantity}</td>
            <td>${stock.expiry}</td>
            <td>${stock.location}</td>
        `;
        tableBody.appendChild(row);
    });

    // 재고 추가 버튼 클릭 이벤트
    document.getElementById("addStockBtn").addEventListener("click", () => {
        alert("재고 추가 기능은 구현 중입니다!");
    });
});
