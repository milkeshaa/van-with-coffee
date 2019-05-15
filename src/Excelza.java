public class Excelza extends Coffee {
  private static int amountOfPackages = 0;
  private static int amountOfJars = 0;
  private static final double PACKAGE_VOLUME = 0.3;
  private static final double JAR_VOLUME = 1.5;

  public Excelza (String typeOfPackage, String state)
  {
    this.state = state;
    this.typeOfPackage = typeOfPackage;

    if (this.state.equals("GRAIN")) {
      this.price = 20;
    } else if (this.state.equals("GROUND")) {
      this.price = 15;
    } else {
      this.price = 10;
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
    return this.typeOfPackage.equals("JAR") ? Excelza.JAR_VOLUME : Excelza.PACKAGE_VOLUME;
  }

  @Override
  protected double calculateTotalPrice() {
    return Excelza.amountOfPackages * this.price + Excelza.amountOfJars * this.price;
  }

  @Override
  protected double calculateTotalVolume() {
    return Excelza.amountOfPackages * Excelza.PACKAGE_VOLUME + Excelza.amountOfJars * Excelza.JAR_VOLUME;
  }
}
