function setMap(mapText, startText, spotText, hazardText) {
    // 각각의 텍스트를 정수 배열로 변환
    var predefinedSpot = mapText.match(/\d+/g).map(Number);
    var mobileRobot = startText.match(/\d+/g).map(Number);
    var colorBlob = spotText.match(/\d+/g).map(Number);
    var hazard0 = hazardText.match(/\d+/g).map(Number);

    var arr1 = [predefinedSpot, mobileRobot, colorBlob, hazard0];
    return arr1;
}
