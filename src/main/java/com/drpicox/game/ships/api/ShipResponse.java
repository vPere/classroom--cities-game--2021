package com.drpicox.game.ships.api;

import com.drpicox.game.ships.Ship;
import com.drpicox.game.games.api.EntityResponse;
import com.drpicox.game.ships.api.ShipResponse;

import java.util.List;

public class ShipResponse extends EntityResponse {
    private int population;
    private int loadUnloadAmount;
    private boolean loadRequested;
    private boolean unloadRequested;

    public ShipResponse(Ship ship) {
        super(ship.getId());
        this.population = ship.getPopulation();

        var loadUnloadAmount = ship.getLoadUnloadAmount();
        this.loadUnloadAmount = Math.abs(loadUnloadAmount);
        this.loadRequested = loadUnloadAmount > 0;
        this.unloadRequested = loadUnloadAmount < 0;
    }

    public  int getPopulation() {
        return this.population;
    }

    public int getLoadUnloadAmount() {
        return loadUnloadAmount;
    }

    public boolean isLoadRequested() {
        return loadRequested;
    }

    public boolean isUnloadRequested() {
        return unloadRequested;
    }
}
