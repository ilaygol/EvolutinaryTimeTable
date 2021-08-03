package AlgorithmClasses;

public enum eMutation {
    FLIPPING
            {
                @Override
                public void activate() {

                }
            };
    public abstract void activate();
}
