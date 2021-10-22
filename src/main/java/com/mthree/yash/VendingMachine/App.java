package com.mthree.yash.VendingMachine;

import com.mthree.yash.VendingMachine.controller.VendingMachineController;
import com.mthree.yash.VendingMachine.dao.VendingMachineAuditDao;
import com.mthree.yash.VendingMachine.dao.VendingMachineAuditDaoImpl;
import com.mthree.yash.VendingMachine.dao.VendingMachineDao;
import com.mthree.yash.VendingMachine.dao.VendingMachineDaoImpl;
import com.mthree.yash.VendingMachine.service.VendingMachineServiceLayer;
import com.mthree.yash.VendingMachine.service.VendingMachineServiceLayerImpl;
import com.mthree.yash.VendingMachine.ui.UserIO;
import com.mthree.yash.VendingMachine.ui.UserIOConsoleImpl;
import com.mthree.yash.VendingMachine.ui.VendingMachineView;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl();
        VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);

        VendingMachineController controller = new VendingMachineController(view, service);

        controller.run();
    }

}
