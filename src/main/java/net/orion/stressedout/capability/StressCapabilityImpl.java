package net.orion.stressedout.capability;

public class StressCapabilityImpl implements StressCapability {
    private int stress = 0;

    @Override
    public int getStress() {
        return stress;
    }

    @Override
    public void setStress(int stress) {
        this.stress = stress;
    }
}
