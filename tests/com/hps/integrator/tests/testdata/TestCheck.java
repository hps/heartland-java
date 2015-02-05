package com.hps.integrator.tests.testdata;

import PosGateway.Exchange.Hps.Enums;
import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckHolder;

public class TestCheck {
    public static HpsCheck approve() {
        HpsAddress address = new HpsAddress();
        address.setAddress("6860 Dallas Parkway");
        address.setCity("Plano");
        address.setState("TX");
        address.setZip("75024");

        HpsCheckHolder checkHolder = new HpsCheckHolder();
        checkHolder.setAddress(address);
        checkHolder.setDlNumber("1234567");
        checkHolder.setDlState("TX");
        checkHolder.setFirstName("John");
        checkHolder.setLastName("Doe");
        checkHolder.setPhone("1234567890");

        HpsCheck check = new HpsCheck();
        check.setAccountNumber("24413815");
        check.setRoutingNumber("490000018");
        check.setCheckType(Enums.checkTypeType.PERSONAL);
        check.setSecCode("PPD");
        check.setAccountType(Enums.accountTypeType.CHECKING);
        check.setCheckHolder(checkHolder);

        return check;
    }

    public static HpsCheck invalidCheckHolder() {
        HpsAddress address = new HpsAddress();
        address.setAddress("6860 Dallas Parkway");
        address.setCity("Plano");
        address.setState("TX");
        address.setZip("75024");

        HpsCheckHolder checkHolder = new HpsCheckHolder();
        checkHolder.setAddress(address);
        checkHolder.setDlNumber("");
        checkHolder.setDlState("");
        checkHolder.setFirstName("John");
        checkHolder.setLastName("Doe");
        checkHolder.setPhone("");

        HpsCheck check = new HpsCheck();
        check.setAccountNumber("24413815");
        check.setRoutingNumber("490000018");
        check.setCheckType(Enums.checkTypeType.PERSONAL);
        check.setSecCode("PPD");
        check.setAccountType(Enums.accountTypeType.CHECKING);
        check.setCheckHolder(checkHolder);

        return check;
    }

    public static HpsCheck decline() {
        HpsAddress address = new HpsAddress();
        address.setAddress("6860 Dallas Parkway");
        address.setCity("Plano");
        address.setState("TX");
        address.setZip("75024");

        HpsCheckHolder checkHolder = new HpsCheckHolder();
        checkHolder.setAddress(address);
        checkHolder.setDlNumber("1234567");
        checkHolder.setDlState("TX");
        checkHolder.setFirstName("John");
        checkHolder.setLastName("Doe");
        checkHolder.setPhone("1234567890");

        HpsCheck check = new HpsCheck();
        check.setAccountNumber("24413815");
        check.setRoutingNumber("490000034");
        check.setCheckType(Enums.checkTypeType.PERSONAL);
        check.setSecCode("PPD");
        check.setAccountType(Enums.accountTypeType.CHECKING);
        check.setCheckHolder(checkHolder);

        return check;
    }

    public static HpsCheck certification() {
        HpsAddress address = new HpsAddress();
        address.setAddress("123 Main St.");
        address.setCity("Downtown");
        address.setState("NJ");
        address.setZip("12345");

        HpsCheckHolder checkHolder = new HpsCheckHolder();
        checkHolder.setAddress(address);
        checkHolder.setDlNumber("09876543210");
        checkHolder.setDlState("TX");
        checkHolder.setFirstName("John");
        checkHolder.setLastName("Doe");
        checkHolder.setPhone("8003214567");

        HpsCheck check = new HpsCheck();
        check.setAccountNumber("24413815");
        check.setRoutingNumber("490000018");
        check.setCheckType(Enums.checkTypeType.PERSONAL);
        check.setSecCode("PPD");
        check.setAccountType(Enums.accountTypeType.CHECKING);
        check.setCheckHolder(checkHolder);

        return check;
    }
}
