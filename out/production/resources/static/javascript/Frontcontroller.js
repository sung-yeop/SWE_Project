document.write('<script src="javascript/InitialData.js"></script>');
document.write('<script src="javascript/DrawMap.js"></script>');
document.write('<script src="javascript/DrawRobot.js"></script>');
//mapData*********************************************************************************
var mapData;
var path = "[(3, 1), (4, 1), (5, 1), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5)]";
//initialize*********************************************************************************
function initialize(event) {
    event.preventDefault();
    // 입력 데이터 가져오기
    var map = document.getElementById("map").value;
    var start = document.getElementById("start").value;
    var spot = document.getElementById("spot").value;
    var hazard = document.getElementById("hazard").value;

    // 데이터를 JavaScript 객체로 구성
    var data = {
        "map": map,
        "start": start,
        "spot": spot,
        "hazard": hazard
    };

    //json data로 전환
    //최초 데이터를 백엔드로 보내는 부분입니다. 여기서 첫번째 반환은 path이고 path에 저장됩니다.
    /* var jsonData = JSON.stringify(data);

    fetch('xx', { //xx에 백엔드의 엔드포인트 URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => response.json())
        .then(result => {
            path = result; // 백엔드에서 보내는 응답을 콘솔에 출력
        }) */

    //setMap을 통하여 텍스트를 정수 배열로 전환
    mapData = setMap(map, start, spot, hazard);
    drawGrid(mapData[0]);
    drawUnit(mapData[0], mapData[1], mapData[2], mapData[3]);
    drawPath(mapData[0], path);
}
//initialize*********************************************************************************
//proceed*********************************************************************************
function proceed(event) {
    event.preventDefault();
    //지금 가지고 있는 길 정보가 옳바른지 확인한다.

    /* var jsonData = JSON.stringify(path);

    fetch('xx', { //xx에 백엔드의 엔드포인트 URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => response.json())
        .then(result => {
            path = result; // 백엔드에서 보내는 결과로 path 수정
        }) */
        
    /* rotate(mapData[0], mapData[1], path); */
    mapData[1] = drawAfterMove(mapData[0], path);
    drawPath(mapData[0], path);
}


