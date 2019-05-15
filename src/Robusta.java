public class Robusta extends Coffee {
  private static int amountOfPackages = 0;
  private static int amountOfJars = 0;
  private static final double PACKAGE_VOLUME = 0.3;
  private static final double JAR_VOLUME = 1.0;

  public Robusta (String typeOfPackage, String state)
  {
    this.state = state;
    this.typeOfPackage = typeOfPackage;

    if (this.state.equals("GRAIN")) {
      this.price = 30;
    } else if (this.state.equals("GROUND")) {
      this.price = 25;
    } else {
      this.price = 20;
    }

    if (this.typeOfPackage.equals("JAR")) {
      this.priceForJar = 5;
      this.price += this.priceForJar;
    } else {
      this.priceForPackage = 2;
      this.price += this.priceForPackage;
    }
  }

  @Override
  protected double getVolume()
  {
    return this.typeOfPackage.equals("JAR") ? Robusta.JAR_VOLUME : Robusta.PACKAGE_VOLUME;
  }

  @Override
  protected double calculateTotalPrice() {
    return Robusta.amountOfPackages * this.price + Robusta.amountOfJars * this.price;
  }

  @Override
  protected double calculateTotalVolume() {
    return Robusta.amountOfPackages * Robusta.PACKAGE_VOLUME + Robusta.amountOfJars * Robusta.JAR_VOLUME;
  }
}
