package com.hps.integrator.fluent;

import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsArgumentException;
import com.hps.integrator.infrastructure.HpsException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class HpsBuilderAbstract<TSoapGatewayService, TExecutionResult> {
    public List<HpsBuilderValidation> validations;
    public boolean executed = false;
    public TSoapGatewayService service;

    protected ElementTree Et;

    public HpsBuilderAbstract(TSoapGatewayService service) {
        this.validations = new ArrayList<HpsBuilderValidation>();

        this.service = service;
        this.Et = new ElementTree();
        try {
            this.setupValidations();
        } catch(HpsException e) {
            this.validations = new ArrayList<HpsBuilderValidation>();
        }
    }

    public TExecutionResult execute() throws HpsException {
        this.validate();
        this.executed = true;

        return null;
    }

    public void validate() throws HpsException {
        for(HpsBuilderValidation validation : this.validations) {
            try {
                Method callback = this.getClass().getDeclaredMethod(validation.getCallback());
                callback.setAccessible(true);
                boolean result = (Boolean)callback.invoke(this);
                if (!result) {
                    System.out.println(validation.getExceptionMessage());
                    throw new HpsArgumentException(validation.getExceptionMessage());
                }
                callback.setAccessible(false);
            }
            catch(HpsArgumentException e){
                throw e;
            }
            catch(Exception e) {
                throw new HpsException(e.getMessage());
            }
        }
    }

    public HpsBuilderAbstract addValidation(HpsBuilderValidation validation) {
        this.validations.add(validation);
        return this;
    }

    protected void setupValidations() throws HpsException { }
}
