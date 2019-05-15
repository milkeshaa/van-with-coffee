public abstract class Coffee {
  protected String state;
  protected String typeOfPackage;
  protected double priceForPackage;
  protected double priceForJar;
  protected double price;

  protected abstract double calculateTotalVolume();
  protected abstract double calculateTotalPrice();
  protected abstract double getVolume();

  public double getPrice()
  {
    return this.price;
  }

  public String getState()
  {
    return this.state;
  }
}
