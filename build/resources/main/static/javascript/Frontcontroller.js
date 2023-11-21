document.write('<script src="javascript/InitialData.js"></script>');
document.write('<script src="javascript/DrawMap.js"></script>');
document.write('<script src="javascript/DrawRobot.js"></script>');
//mapData*********************************************************************************
var mapData;
let path;
// = "[(3, 1), (4, 1), (5, 1), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 5), (6, 5), (6, 5)]";
var count = 0;
//initialize*********************************************************************************
function initialize(event) {
    event.preventDefault();
    // 입력 데이터 가져오기
    var map = document.getElementById("map").value;
    var start = document.getElementById("start").value;
    var spot = document.getElementById("spot").value;
    var hazard = document.getElementById("hazard").value;
    var colorBlob = document.getElementById("color").value;
    // 데이터를 JavaScript 객체로 구성
    var data = {
        "map": map,
        "start": start,
        "spot": spot,
        "hazard": hazard,
        "colorBlob": colorBlob
    };

    //json data로 전환
    //최초 데이터를 백엔드로 보내는 부분입니다. 여기서 첫번째 반환은 path이고 path에 저장됩니다.
    var jsonData = JSON.stringify(data);

    fetch('/api/init/', { //xx에 백엔드의 엔드포인트 URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => response.json())
        .then(result => {
            path = result.path;

            //setMap을 통하여 텍스트를 정수 배열로 전환
            mapData = setMap(map, start, spot, hazard, colorBlob);
            drawGrid(mapData[0]);
            drawPath(mapData[0], path);
            drawUnit(mapData);
        })
}
//initialize*********************************************************************************
//proceed*********************************************************************************
function proceed(event) {
    event.preventDefault();
    var pos;
    document.getElementById('information').innerHTML = '이동중';

    //지금 가지고 있는 길 정보가 옳바른지 확인한다.
    var jsonData = JSON.stringify(path.slice(1, 7));

    fetch(' /api/move/', { //xx에 백엔드의 엔드포인트 URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => response.json())
        .then(result => {
            pos = result.currentPosition;
            if (result.path != null) {
                path = result.path;
            }

            mapData[1] = drawAfterMove(mapData[0], pos);
            path = path.slice(0, 1) + path.slice(9);
            drawPath(mapData[0], path);
        })

    /* rotate(mapData[0], mapData[1], path); */
}
//update*********************************************************************************
function update(event) {
    event.preventDefault();
    var mike = document.getElementById("mike");
    if (count == 0) {
        document.getElementById('information').innerHTML = '녹음중';
        mike.style.transform = 'rotate(' + -45 + 'deg)';
        mike.style.boxShadow = '5px 5px 5px rgba(0, 0, 0, 0.5)';
        //음성 데이터 파일을 전환하는 부분은 추가되야함
        // 그래서 colorBlob과 추가된 hazard는 임의로 설정하였음
        var colorBlob = [2, 2, 3, 3];
        var addhazard = [3, 4];
        drawVoice(mapData, colorBlob, addhazard);
        count++;
    } else {
        document.getElementById('information').innerHTML = '녹음 완료';

        mike.style.transform = 'rotate(' + 0 + 'deg)';
        mike.style.boxShadow = '0px 0px 0px rgba(0, 0, 0, 0)';

        count = 0;
    }

}
//update*********************************************************************************