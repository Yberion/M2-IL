var editingMode = { rect: 0, line: 1 };

function Pencil(ctx, drawing, canvas) {
    this.currEditingMode = editingMode.line;
    this.currLineWidth = 5;
    this.currColour = "#000000";
    this.currentShape = 0;

    document.getElementById("butRect").addEventListener(
        "click",
        () => {
            this.currentShape = editingMode.rect;
        },
        false
    );
    document.getElementById("butLine").addEventListener(
        "click",
        (evt) => {
            this.currentShape = editingMode.line;
        },
        false
    );
    document.getElementById("colour").addEventListener(
        "change",
        (evt) => {
            console.log(evt);
            this.currColour = evt.target.value;
        },
        false
    );

    if (this.currEditingMode == editingMode.line) {
        this.currentShape = new Line(0, 0, 0, 0, this.currLineWidth, this.currColour);
    } else {
        this.currentShape = new Rectangle(0, 0, 0, 0, this.currLineWidth, this.currColour);
    }

    // Liez ici les widgets à la classe pour modifier les attributs présents ci-dessus.
    // Récupérer les infos via le DOM ?

    new DnD(canvas, this);

    // Implémentez ici les 3 fonctions onInteractionStart, onInteractionUpdate et onInteractionEnd
    // Faut gérer quand c'est Line ou Rectangle de sélectionné
    this.onInteractionStart = function (dnd) {
        this.currentShape.xStart = dnd.xStart;
        this.currentShape.yStart = dnd.yStart;
        drawing.paint(ctx);
    }.bind(this);

    this.onInteractionUpdate = function (dnd) {
        this.currentShape.xEnd = dnd.xEnd;
        this.currentShape.yEnd = dnd.yEnd;
        drawing.paint(ctx);
    }.bind(this);

    this.onInteractionEnd = function (dnd) {
        this.currentShape.xEnd = dnd.xEnd;
        this.currentShape.yEnd = dnd.yEnd;

        drawing.forms.push(this.currentShape);

        drawing.paint(ctx);
    }.bind(this);
}
