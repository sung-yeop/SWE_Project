//drawAfterMove*********************************************************************************
function drawAfterMove(mapdata, path) {
    var patharr = path.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    cols = mapdata[0];
    rows = mapdata[1];

    var mapgrid = document.getElementById("myGrid");
    var cellwidth = mapgrid.offsetWidth / cols;
    var cellheight = mapgrid.offsetHeight / rows;

    var robotpos = document.getElementById("robot");
    robotwidth = robotpos.offsetWidth / 2;
    robotheight = robotpos.offsetHeight / 2;

    robotpos.style.left = -robotwidth + cellwidth * patharr[0] + "px";
    robotpos.style.top = -robotheight + cellheight * (rows - patharr[1]) + "px";

    return [patharr[0], patharr[1]];
}
//drawAfterMove*********************************************************************************
//rotate*********************************************************************************
function rotate(currpos, path) {
    var patharr = path.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    var left = patharr[0] - currpos[0];
    var top = patharr[1] - currpos[1];

    var image = document.getElementById('robot');
    var desiredAngle = -90; // 이미지가 왼쪽을 향하도록 -90도 회전

    image.style.transform = 'rotate(' + desiredAngle + 'deg)';
}