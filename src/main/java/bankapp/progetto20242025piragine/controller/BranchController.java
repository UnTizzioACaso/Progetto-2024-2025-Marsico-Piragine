package bankapp.progetto20242025piragine.controller;

import bankapp.progetto20242025piragine.controller.popup.PinPopupController;

public abstract class BranchController {

    private PinPopupController pinPopupController;

    public PinPopupController getPinPopupController() {
        return pinPopupController;
    }

    public void setPinPopupController(PinPopupController pinPopupController) {
        this.pinPopupController = pinPopupController;
    }

}
