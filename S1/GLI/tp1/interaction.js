// La création d'un Dnd requière un canvas et un interacteur.
// L'interacteur viendra dans un second temps donc ne vous en souciez pas au départ.
function DnD(canvas, interactor) {
    // Définir ici les attributs de la 'classe'
    this.xStart = 0;
    this.yStart = 0;
    this.xEnd = 0;
    this.yEnd = 0;
    this.mouseClicked = false;

    // Associer les fonctions précédentes aux évènements du canvas.
    canvas.addEventListener('mousedown', this.maFctGerantLaPression, false);
    canvas.addEventListener('mousemove', this.maFctGerantLeDeplacement, false);
    canvas.addEventListener('mouseup', this.maFctGerantLeRelachement, false);
};

// Developper les 3 fonctions gérant les événements
DnD.prototype.maFctGerantLaPression = function(evt) {
    this.xStart = evt.x;
    this.yStart = evt.y;
    this.mouseClicked = true;
    console.log("Pression, xStart = " + this.xStart + ", yStart = " + this.yStart + ", xEnd = " + this.xEnd + ", yEnd = " + this.yEnd + ", clicked = " + this.mouseClicked)
};

DnD.prototype.maFctGerantLeDeplacement = function(evt) {
    if (this.mouseClicked) {
        this.xEnd = evt.x;
        this.yEnd = evt.y;
        console.log("Déplacement, xStart = " + this.xStart + ", yStart = " + this.yStart + ", xEnd = " + this.xEnd + ", yEnd = " + this.yEnd + ", clicked = " + this.mouseClicked)
    }
};

DnD.prototype.maFctGerantLeRelachement = function(evt) {
    if (this.mouseClicked) {
        this.mouseClicked = false;
        this.xEnd = evt.x;
        this.yEnd = evt.y;
        console.log("Relâchement, xStart = " + this.xStart + ", yStart = " + this.yStart + ", xEnd = " + this.xEnd + ", yEnd = " + this.yEnd + ", clicked = " + this.mouseClicked)
    }
};



// Place le point de l'événement evt relativement à la position du canvas.
function getMousePosition(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left,
        y: evt.clientY - rect.top
    };
};