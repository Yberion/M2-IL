export default class Todo {
    constructor(private _name: string, private _checked: boolean, private _done: boolean) {}

    get name(): string {
        return this._name;
    }

    set name(newName: string) {
        this._name = newName;
    }

    get checked(): boolean {
        return this._checked;
    }

    set checked(newChecked: boolean) {
        this._checked = newChecked;
    }

    get done(): boolean {
        return this._done;
    }

    set done(newDone: boolean) {
        this._done = newDone;
    }
}
