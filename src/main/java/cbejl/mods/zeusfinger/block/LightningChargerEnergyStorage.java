package cbejl.mods.zeusfinger.block;

import net.minecraftforge.energy.EnergyStorage;

public class LightningChargerEnergyStorage extends EnergyStorage {
    public LightningChargerEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public void addEnergy(int energy) {
        this.energy = this.energy + energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
