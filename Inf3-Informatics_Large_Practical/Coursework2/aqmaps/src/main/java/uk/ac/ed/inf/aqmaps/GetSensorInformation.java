package uk.ac.ed.inf.aqmaps;

public class GetSensorInformation {
     
    /**
     * 
     * @param String
     * @return String
     * using string reading to get the corresponding RGB string
     */
    public static String getRGB(String str) {

        Double number = Double.parseDouble(str);

        if ((0 <= number) && (number < 32)) {
            return "#00ff00";
        }
        if ((32 <= number) && (number < 64)) {
            return "#40ff00";
        }
        if ((64 <= number) && (number < 96)) {
            return "#80ff00";
        }
        if ((96 <= number) && (number < 128)) {
            return "#c0ff00";
        }
        if ((128 <= number) && (number < 160)) {
            return "#ffc000";
        }
        if ((160 <= number) && (number < 192)) {
            return "#ff8000";
        }
        if ((192 <= number) && (number < 224)) {
            return "#ff4000";
        }
        if ((224 <= number) && (number < 256)) {
            return "#ff0000";
        }
        if (number == -1.0) {
            return "#000000";
        }
        // not visited string = "#aaaaaa
        else {
            return null;
        }
    }
    
    /**
     * 
     * @param String
     * @return String
     * using string reading to get the corresponding symbol String
     */
    public static String getSymbol(String str) {
        Double number = Double.parseDouble(str);

        if ((0 <= number) && (number < 128)) {
            return "lighthouse";
        }

        if ((128 <= number) && (number < 256)) {
            return "danger";
        }

        if (number == -1.0) {
            return "cross";
        }
        // not visited string = "#aaaaaa
        else {
            return null;
        }
    }
}
