import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Van {
  private double volume;
  private double leftVolume;
  private static ArrayList<Coffee> coffee = new ArrayList<>();
  private double totalPrice;
  private double leftMoney;

  public Van (double volume, double price)
  {
    this.volume = volume;
    this.totalPrice = price;
    this.leftMoney = this.totalPrice;
    this.leftVolume = this.volume;
  }

  private boolean moneyNotFullyWasted()
  {
    return this.leftMoney < this.totalPrice && this.leftMoney > 0;
  }

  private boolean notFullyEquipped()
  {
    return this.leftVolume < this.volume && this.leftVolume > 0;
  }

  private boolean isEnoughMoneyForCoffee(Coffee coffee)
  {
    return this.leftMoney >= coffee.getPrice();
  }

  private boolean isEnoughVolumeForCoffee(Coffee coffee)
  {
    return this.leftVolume >= coffee.getVolume();
  }

  private void buyCoffee(Coffee coffee, double coffeeVolume)
  {
    int counter = 0;
    try {
      if (this.isEnoughVolumeForCoffee(coffee)) {
        try {
          if (this.isEnoughMoneyForCoffee(coffee)) {
            this.leftVolume -= coffeeVolume;
            while (coffeeVolume >= coffee.getVolume() && this.leftMoney > 0) {
              this.leftMoney -= coffee.getPrice();
              coffeeVolume -= coffee.getVolume();
              Van.coffee.add(coffee);
              counter++;
            }
            System.out.println("Было куплено " + counter + " упаковок кофе с заданными настройками.");
          } else {
            throw new Exception("Не хватает денег!");
          }
        } catch (Exception e) {
          System.out.println(e);
        }
      } else {
        throw new Exception("Не хватает свободного места!");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void sortPurchase()
  {
    double[] priceArray = new double[Van.coffee.size()];
    double[] volumeArray = new double[Van.coffee.size()];
    for (int i = 0; i < priceArray.length; i++) {
      priceArray[i] = Van.coffee.get(i).getPrice();
    }
    for (int i = 0; i < volumeArray.length; i++) {
      volumeArray[i] = Van.coffee.get(i).getVolume();
    }

    double temp;
    Coffee coffeeTemp;
    int j;
    for (int i = 0; i < volumeArray.length - 1; i++) {
      if (volumeArray[i] > volumeArray[i + 1]) {
        temp = volumeArray[i + 1];
        coffeeTemp = Van.coffee.get(i + 1);
        volumeArray[i + 1] = volumeArray[i];
        Van.coffee.set(i + 1, Van.coffee.get(i));
        j = i;
        while (j > 0 && temp < volumeArray[j - 1]) {
          volumeArray[j] = volumeArray[j - 1];
          Van.coffee.set(j, Van.coffee.get(j - 1));
          j--;
        }
        Van.coffee.set(j, coffeeTemp);
        volumeArray[j] = temp;
      }
    }

    for (int i = 0; i < Van.coffee.size(); i++) {
      for (int k = 0; k < Van.coffee.size(); k++) {
        if (Van.coffee.get(i).getVolume() == Van.coffee.get(k).getVolume()) {
          if (Van.coffee.get(k).getPrice() > Van.coffee.get(i).getPrice()) {
            Coffee cofTemp = Van.coffee.get(i);
            Van.coffee.set(i, Van.coffee.get(k));
            Van.coffee.set(k, cofTemp);
          }
        }
      }
    }
  }

  private void showPurchase()
  {
    for (int i = 0; i < Van.coffee.size(); i++) {
      System.out.println("Кофе: " + Van.coffee.get(i) +
              ", тип: " + Van.coffee.get(i).getState() +
              ", упаковка: " + Van.coffee.get(i).typeOfPackage +
              ", объём: " + Van.coffee.get(i).getVolume() +
              ", цена: " + Van.coffee.get(i).getPrice());
    }
  }

  private void findInPurchase(double volumeFrom, double volumeTo, String state)
  {
    for (int i = 0; i < Van.coffee.size(); i++) {
      if (Van.coffee.get(i).getState().equals(state) && (Van.coffee.get(i).getVolume() >= volumeFrom && Van.coffee.get(i).getVolume() <= volumeTo)) {
        System.out.println("Кофе: " + Van.coffee.get(i) +
                ", тип: " + Van.coffee.get(i).getState() +
                ", упаковка: " + Van.coffee.get(i).typeOfPackage +
                ", объём: " + Van.coffee.get(i).getVolume() +
                ", цена: " + Van.coffee.get(i).getPrice());
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("Введите объем фургона и сумму закупки");
    Scanner scanner = new Scanner(System.in);
    Van van = new Van(scanner.nextDouble(), scanner.nextDouble());

    while (van.leftVolume > 0 && van.leftMoney > 0) {
      String formattedDouble = new DecimalFormat("#0.00").format(van.leftVolume);
      System.out.println("Введите объем покупки (до " + formattedDouble + ")");
      double volume = scanner.nextDouble();
      System.out.println("Какой кофе хотите купить?");
      System.out.println("Введите: 1 - Эксцельза, 2 - Робуста, 3 - Либерика, 4 - Арабика");
      System.out.println("Затем введите тип кофе: GROUND, GRAIN, SOLUBLE");
      System.out.println("Затем введите тип упаковки: PACKAGE, JAR");
      int cofNum = scanner.nextInt();
      String cofState = scanner.next();
      String cofPackage = scanner.next();
      switch (cofNum) {
        case 1: {
          switch (cofState) {
            case "GROUND": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Excelza("JAR", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Excelza("PACKAGE", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "GRAIN": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Excelza("JAR", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Excelza("PACKAGE", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "SOLUBLE": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Excelza("JAR", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Excelza("PACKAGE", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
          }
          break;
        }
        case 2: {
          switch (cofState) {
            case "GROUND": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Robusta("JAR", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Robusta("PACKAGE", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "GRAIN": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Robusta("JAR", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Robusta("PACKAGE", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "SOLUBLE": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Robusta("JAR", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Robusta("PACKAGE", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
          }
          break;
        }
        case 3: {
          switch (cofState) {
            case "GROUND": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Liberica("JAR", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Liberica("PACKAGE", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "GRAIN": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Liberica("JAR", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Liberica("PACKAGE", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "SOLUBLE": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Liberica("JAR", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Liberica("PACKAGE", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
          }
          break;
        }
        case 4: {
          switch (cofState) {
            case "GROUND": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Arabica("JAR", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Arabica("PACKAGE", "GROUND");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "GRAIN": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Arabica("JAR", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Arabica("PACKAGE", "GRAIN");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
            case "SOLUBLE": {
              switch (cofPackage) {
                case "JAR": {
                  Coffee coffee = new Arabica("JAR", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
                case "PACKAGE": {
                  Coffee coffee = new Arabica("PACKAGE", "SOLUBLE");
                  van.buyCoffee(coffee, volume);
                  break;
                }
              }
              break;
            }
          }
          break;
        }
      }
    }

    if (van.notFullyEquipped()) {
      System.out.println("Фургон не заполнен полностью, осталось: " + van.leftVolume + " объёма");
    }
    if (van.moneyNotFullyWasted()) {
      System.out.println("Сумма покупки истрачена не полностью. Осталось: " + van.leftMoney);
    }

    van.sortPurchase();
    van.showPurchase();

    System.out.println("Найти товар в фургоне, соответствующий заданному диапазону параметров качества: ");
    van.findInPurchase(scanner.nextDouble(), scanner.nextDouble(), scanner.next());
  }
}
