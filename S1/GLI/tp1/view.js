// Implémenter ici les fonctions paint à ajouter dans chacune des classes du modèle.

Drawing.prototype.paint = function (ctx, canvas) {
    //console.log(this.forms);
    ctx.fillStyle = "#aaa"; // set canvas' background color
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // Code temporaire pour tester l'affiche de la vue
    var rec = new Rectangle(10, 20, 50, 100, 5, "#00CCC0");
    rec.paint(ctx);
    var ligne = new Line(10, 20, 50, 100, 5, "#00CCC0");
    ligne.paint(ctx);

    this.forms.forEach((eltDuTableau) => eltDuTableau.paint(ctx));
};

Form.prototype.paint = function (ctx) {
    ctx.strokeStyle = this.color;
    ctx.lineWidth = this.lineWidth;
};

Rectangle.prototype.paint = function (ctx) {
    
    Form.prototype.paint.call(this, ctx);
    ctx.beginPath();
    ctx.rect(this.xStart, this.yStart, this.width, this.height);
    ctx.stroke();
};

Line.prototype.paint = function (ctx) {
    Form.prototype.paint.call(this, ctx);
    ctx.beginPath();
    ctx.moveTo(this.xStart, this.yStart);
    ctx.lineTo(this.xEnd, this.yEnd);
    ctx.stroke();
};
