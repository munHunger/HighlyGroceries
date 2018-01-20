package oven.injection;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import oven.dao.GrocerieDAO;
import oven.service.TestService;

public class Binder extends AbstractBinder{
    @Override
    protected void configure() {
        bind(GrocerieDAO.class).to(GrocerieDAO.class);
        bind(TestService.class).to(TestService.class);

    }
}
