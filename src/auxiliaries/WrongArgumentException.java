package auxiliaries;

public class WrongArgumentException extends Exception {
    String argName;
    Number argValue;
    Number argSegmentStart;
    Number argSegmentEnd;

    public WrongArgumentException(String argName, Number argValue, Number argSegmentStart, Number argSegmentEnd){
        this.argName = argName;
        this.argValue = argValue;
        this.argSegmentStart = argSegmentStart;
        this.argSegmentEnd = argSegmentEnd;
    }

    @Override
    public String getMessage(){
        return "Argument " + argName + " shouldn't be " + argValue + ", it should take values from " + argSegmentStart + " to " + argSegmentEnd;
    }
}
