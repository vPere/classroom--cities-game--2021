package com.drpicox.game.entities.materials;

import com.drpicox.game.components.loadables.LoadablesController;
import com.drpicox.game.components.locateds.LocatedsController;
import com.drpicox.game.components.nameds.NamedsController;
import com.drpicox.game.components.owneds.OwnedsController;
import com.drpicox.game.components.populateds.PopulatedsController;
import com.drpicox.game.components.quantity.QuantityController;
import com.drpicox.game.components.sails.SailsController;
import com.drpicox.game.components.typeds.TypedsController;
import com.drpicox.game.ecs.EntityIdGenerator;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class MaterialFactory {

    private final NamedsController namedsController;
    private final OwnedsController ownedsController;
    private final QuantityController quantityController;
    private final EntityIdGenerator entityIdGenerator;
    private final TypedsController typedsController;

    public MaterialFactory(NamedsController namedsController, OwnedsController ownedsController, EntityIdGenerator entityIdGenerator,TypedsController typedsController, QuantityController quantityController) {
        this.namedsController = namedsController;
        this.ownedsController = ownedsController;
        this.entityIdGenerator = entityIdGenerator;
        this.quantityController = quantityController;
        this.typedsController = typedsController;
    }

    public void createMaterials(Game game, Player owner, String materialName, int initialQuantity) {
        var entityId = entityIdGenerator.nextEntityId("material");
        namedsController.create(entityId, game, materialName);
        ownedsController.create(entityId, game, owner);
        typedsController.create(entityId, game, "material");
        quantityController.create(entityId, game, initialQuantity);
    }

}