document.write('<script src="javascript/InitialData.js"></script>');
document.write('<script src="javascript/DrawMap.js"></script>');
document.write('<script src="javascript/DrawRobot.js"></script>');
document.write('<script src="javascript/VocalData.js"></script>');
//mapData*********************************************************************************
var mapData;
let path;
let isRecognizing = true;

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
    var jsonData = JSON.stringify({"path": path.match(/\([^)]+\)/g)[0]});
    // var jsonData = JSON.stringify({path: path.slice()});


    fetch('/api/move/', { //xx에 백엔드의 엔드포인트 URL
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
            if (result.hazardList != null) {
                mapData[3] = mapData[3].concat(result.hazardList.match(/\d+/g).map(Number));
                console.log(result.hazardList);
            }
            if (result.colorBlobList != null) {
                mapData[4] = mapData[4].concat(result.colorBlobList.match(/\d+/g).map(Number));
            }
            drawUnit(mapData);
            rotate(path, pos);
            drawAfterMove(mapData[0], pos);
            var firstBracketIndex = path.indexOf('(');
            var secondBracketIndex = path.indexOf(')', firstBracketIndex + 1);
            if (firstBracketIndex !== -1 && secondBracketIndex !== -1) {
                path = path.substring(0, firstBracketIndex) + path.substring(secondBracketIndex);
            }
            drawPath(mapData[0], path);
        })
    // rotate(mapData[0], mapData[1], path);
}

//update*********************************************************************************
function update(event) {
    event.preventDefault();
    var mike = document.getElementById('mike');

    if (isRecognizing) {
        document.getElementById('information').innerHTML = '녹음중';
        mike.style.transform = 'rotate(' + -45 + 'deg)';
        mike.style.boxShadow = '5px 5px 5px rgba(0, 0, 0, 0.5)';
        startRecording();
        isRecognizing = false;
    } else {
        // document.getElementById('information').innerHTML = '녹음 완료';
        mike.style.transform = 'rotate(' + 0 + 'deg)';
        mike.style.boxShadow = '0px 0px 0px rgba(0, 0, 0, 0)';

        let vocalData = stopRecording();
        var jsonData = JSON.stringify(vocalData);

        fetch('/api/vocal/', { //xx에 백엔드의 엔드포인트 URL
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: jsonData
        })
            .then(response => response.json())

        isRecognizing = true;
    }
}

//update*********************************************************************************



