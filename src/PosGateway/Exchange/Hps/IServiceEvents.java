package PosGateway.Exchange.Hps;

public interface IServiceEvents {
    public void Starting();

    public void Completed(OperationResult result);
}
