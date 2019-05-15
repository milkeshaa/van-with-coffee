public class Liberica extends Coffee {
  private static int amountOfPackages = 0;
  private static int amountOfJars = 0;
  private static final double PACKAGE_VOLUME = 0.3;
  private static final double JAR_VOLUME = 1.5;

  public Liberica (String typeOfPackage, String state)
  {
    this.state = state;
    this.typeOfPackage = typeOfPackage;

    if (this.state.equals("GRAIN")) {
      this.price = 40;
    } else if (this.state.equals("GROUND")) {
      this.price = 35;
    } else {
      this.price = 30;
    }

    if (this.typeOfPackage.equals("JAR")) {
      this.priceForJar = 7;
      this.price += this.priceForJar;
    } else {
      this.priceForPackage = 3;
      this.price += this.priceForPackage;
    }
  }

  @Override
  protected double getVolume()
  {
    return this.typeOfPackage.equals("JAR") ? Liberica.JAR_VOLUME : Liberica.PACKAGE_VOLUME;
  }

  @Override
  protected double calculateTotalPrice() {
    return Liberica.amountOfPackages * this.price + Liberica.amountOfJars * this.price;
  }

  @Override
  protected double calculateTotalVolume() {
    return Liberica.amountOfPackages * Liberica.PACKAGE_VOLUME + Liberica.amountOfJars * Liberica.JAR_VOLUME;
  }
}
