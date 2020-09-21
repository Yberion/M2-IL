// Implémenter ici les 4 classes du modèle.
// N'oubliez pas l'héritage !
function Drawing() {
    this.forms = new Array();
}

function Form(color, lineWidth) {
    this.color = color;
    this.lineWidth = lineWidth;
}

function Rectangle(xStart, yStart, width, height, lineWidth, color) {
    Form.call(this, color, lineWidth);

    this.xStart = xStart;
    this.yStart = yStart;
    this.width = width;
    this.height = height;
}

Rectangle.prototype = new Form();

function Line(xStart, yStart, xEnd, yEnd, lineWidth, color) {
    Form.call(this, color, lineWidth);

    this.xStart = xStart;
    this.yStart = yStart;
    this.xEnd = xEnd;
    this.yEnd = yEnd;
}

Line.prototype = new Form();
