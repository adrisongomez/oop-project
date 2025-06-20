package com.oopecommerce.notifications;

import com.oopecommerce.models.inventory.InventoryLevel;

/**
 * Evento emitido cuando cambia la cantidad de un producto en inventario.
 */
public class InventoryLevelChangeEvent implements NotificationEvent {
    private final InventoryLevel level;
    private final int oldQuantity;
    private final int newQuantity;

    public InventoryLevelChangeEvent(InventoryLevel level, int oldQuantity, int newQuantity) {
        this.level = level;
        this.oldQuantity = oldQuantity;
        this.newQuantity = newQuantity;
    }

    public InventoryLevel getLevel() { return level; }
    public int getOldQuantity() { return oldQuantity; }
    public int getNewQuantity() { return newQuantity; }
}
