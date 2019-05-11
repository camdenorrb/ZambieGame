/*
@Deprecated(message = "Use TryUtils instead")
public class TryOrDefault {

    private final TryBlock block;


    public TryOrDefault(TryBlock block) {
        this.block = block;
    }


    public T getOrDefault(T defaultValue) {
        tryblock { block.attempt(); }
        catch (Exception e) { e.printStackTrace(); }
        return defaultValue;
    }

}*/