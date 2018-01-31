package se.munhunger.oven.injection;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import se.munhunger.oven.dao.GrocerieDAO;
import se.munhunger.oven.service.Groceries;

public class Binder extends AbstractBinder{
    @Override
    protected void configure() {
        bind(GrocerieDAO.class).to(GrocerieDAO.class);
        bind(Groceries.class).to(Groceries.class);

    }
}