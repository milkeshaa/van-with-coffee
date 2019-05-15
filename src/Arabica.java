public class Arabica extends Coffee {
  private static int amountOfPackages = 0;
  private static int amountOfJars = 0;
  private static final double PACKAGE_VOLUME = 0.3;
  private static final double JAR_VOLUME = 1.5;

  public Arabica (String typeOfPackage, String state)
  {
    this.state = state;
    this.typeOfPackage = typeOfPackage;

    if (this.state.equals("GRAIN")) {
      this.price = 50;
    } else if (this.state.equals("GROUND")) {
      this.price = 45;
    } else {
      this.price = 40;
    }

    if (this.typeOfPackage.equals("JAR")) {
      this.priceForJar = 8;
      this.price += this.priceForJar;
    } else {
      this.priceForPackage = 5;
      this.price += this.priceForPackage;
    }
  }

  @Override
  protected double getVolume()
  {
    return this.typeOfPackage.equals("JAR") ? Arabica.JAR_VOLUME : Arabica.PACKAGE_VOLUME;
  }

  @Override
  protected double calculateTotalPrice() {
    return Arabica.amountOfPackages * this.price + Arabica.amountOfJars * this.price;
  }

  @Override
  protected double calculateTotalVolume() {
    return Arabica.amountOfPackages * Arabica.PACKAGE_VOLUME + Arabica.amountOfJars * Arabica.JAR_VOLUME;
  }
}
