package gr.foodNhealth.model;

public enum AvailableForm {
    GRAMS("grams"),
    PIECES("pieces"),
    SLICES("slices");

    private final String form;

    AvailableForm (String form) {
        this.form = form;
    }

    public String getForm () {
        return form;
    }

    public Integer getGrams () {
        Integer grams;
        switch (form) {
            case "grams":
                grams = 1;
                break;
            case "pieces":
                grams = 10; // TODO write how many grams a piece is
                break;
            case "slices":
                grams = 10; // TODO write how many grams a slice is
                break;
            default:
                grams = 0;
                break;
        }
        return grams;
    }
}
