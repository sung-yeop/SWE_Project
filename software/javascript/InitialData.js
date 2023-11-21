//setMap********************************************************
function setMap(mapText, startText, spotText, hazardText, colorBlob) {
    // 각각의 텍스트를 정수 배열로 변환
    var predefinedSpot = mapText.match(/\d+/g).map(Number);
    var mobileRobot = startText.match(/\d+/g).map(Number);
    var spot = spotText.match(/\d+/g).map(Number);
    var hazard0 = hazardText.match(/\d+/g).map(Number);
    var colorBlob1 = colorBlob.match(/\d+/g).map(Number);

    var arr1 = [predefinedSpot, mobileRobot, spot, hazard0, colorBlob1];
    return arr1;
}

//extractIntegers********************************************************
function extractIntegers(vector) {
    return vector
        .match(/\d+/g) // 정규 표현식을 사용해 숫자만 추출
        .join(', ');   // 추출된 숫자를 문자열로 조합하여 반환
}
