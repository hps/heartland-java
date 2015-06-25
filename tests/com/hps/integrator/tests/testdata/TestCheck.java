package com.hps.integrator.tests.testdata;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckHolder;
import com.hps.integrator.infrastructure.emums.AccountTypeType;
import com.hps.integrator.infrastructure.emums.CheckTypeType;

public class TestCheck {
    public static HpsAddress heartlandAddress() {
        HpsAddress address = new HpsAddress();

        address.setAddress("6860 Dallas Pkwy");
        address.setCity("Irvine");
        address.setState("TX");
        address.setZip("75024");
        address.setCountry("United States");

        return address;
    }

    private static HpsCheckHolder checkHolder() {
        HpsCheckHolder checkHolder = new HpsCheckHolder();
        checkHolder.setAddress(heartlandAddress());
        checkHolder.setDlNumber("1234567");
        checkHolder.setDlState("TX");
        checkHolder.setFirstName("John");
        checkHolder.setLastName("Doe");
        checkHolder.setPhone("1234567890");

        return checkHolder;
    }

    public static HpsCheck goodCheck() {
        HpsCheck check = new HpsCheck();
        check.setAccountNumber("24413815");
        check.setRoutingNumber("490000018");
        check.setCheckType(CheckTypeType.personal);
        check.setSecCode("PPD");
        check.setAccountType(AccountTypeType.checking);
        check.setCheckHolder(checkHolder());

        return check;
    }

    public static HpsCheck badCheck() {
        HpsCheck check = goodCheck();
        check.setRoutingNumber("490000034");

        return check;
    }

    public static HpsCheck certCheck() {
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
        checkHolder.setDobYear("1985");

        HpsCheck check = new HpsCheck();
        check.setAccountNumber("24413815");
        check.setRoutingNumber("490000018");
        check.setCheckType(CheckTypeType.personal);
        check.setSecCode("PPD");
        check.setAccountType(AccountTypeType.checking);
        check.setCheckHolder(checkHolder);

        return check;
    }
}
