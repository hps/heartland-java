package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTransactionType;
import com.hps.integrator.entities.credit.HpsReportTransactionSummary;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditListBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsReportTransactionSummary[]> {
    Date utcStartDate;
    Date utcEndDate;
    HpsTransactionType filterBy;

    public CreditListBuilder withUtcStartDate(Date startDate) {
        this.utcStartDate = startDate;
        return this;
    }
    public CreditListBuilder withUtcEndDate(Date endDate) {
        this.utcEndDate = endDate;
        return this;
    }
    public CreditListBuilder withFilterBy(HpsTransactionType filterBy) {
        this.filterBy = filterBy;
        return this;
    }

    public CreditListBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsReportTransactionSummary[] execute() throws HpsException {
        super.execute();

        HpsInputValidation.checkDateNotFuture(utcStartDate, "Start Date");
        HpsInputValidation.checkDateNotFuture(utcEndDate, "End Date");
        service.setFilterBy(filterBy);

        Element transaction = Et.element("ReportActivity");
        Et.subElement(transaction, "RptStartUtcDT").text(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(utcStartDate));
        Et.subElement(transaction, "RptEndUtcDT").text(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(utcEndDate));

        ElementTree response = service.submitTransaction(transaction);
        return new HpsReportTransactionSummary().fromElementTree(response, filterBy);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("startDateIsNotNull", "Start Date is required."));
        this.addValidation(new HpsBuilderValidation("endDateIsNotNull", "End Date is required."));
    }

    private boolean startDateIsNotNull(){
        return this.utcStartDate != null;
    }

    private boolean endDateIsNotNull(){
        return this.utcEndDate != null;
    }
}
