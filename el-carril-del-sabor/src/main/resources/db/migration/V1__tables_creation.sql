-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`users` (
  `id_user` BIGINT NOT NULL AUTO_INCREMENT,
  `auth0_id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `user_type` ENUM('ADMIN', 'CUSTOMER', 'DELIVERY') NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`customers` (
  `id_customer` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `id_user` BIGINT NOT NULL,
  PRIMARY KEY (`id_customer`),
  INDEX `fk_customers_users_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_customers_users`
    FOREIGN KEY (`id_user`)
    REFERENCES `elcarrildelsabor_db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`food_outlets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`food_outlets` (
  `id_food_outlet` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `phone_number` VARCHAR(12) NOT NULL,
  `morning_opening_hours` DATETIME NULL,
  `morning_closing_hours` DATETIME NULL,
  `evening_opening_hours` DATETIME NULL,
  `evening_closing_hours` DATETIME NULL,
  PRIMARY KEY (`id_food_outlet`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`administrators`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`administrators` (
  `id_admin` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `id_user` BIGINT NOT NULL,
  `food_outlet` BIGINT NOT NULL,
  PRIMARY KEY (`id_admin`),
  INDEX `fk_admins_users_idx` (`id_user` ASC) VISIBLE,
  INDEX `fk_administrators_food_outlets_idx` (`food_outlet` ASC) VISIBLE,
  CONSTRAINT `fk_admins_users`
    FOREIGN KEY (`id_user`)
    REFERENCES `elcarrildelsabor_db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_administrators_food_outlets`
    FOREIGN KEY (`food_outlet`)
    REFERENCES `elcarrildelsabor_db`.`food_outlets` (`id_food_outlet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`dishes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`dishes` (
  `id_dish` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `price` DECIMAL(10,2) NULL,
  `available` TINYINT NULL DEFAULT 1,
  `modify_by` BIGINT NULL,
  `img` MEDIUMBLOB NOT NULL,
  `food_outlet` BIGINT NOT NULL,
  PRIMARY KEY (`id_dish`),
  INDEX `fk_dishes_administrators_idx` (`modify_by` ASC) VISIBLE,
  INDEX `fk_dishes_food_outlets_idx` (`food_outlet` ASC) VISIBLE,
  CONSTRAINT `fk_dishes_administrators`
    FOREIGN KEY (`modify_by`)
    REFERENCES `elcarrildelsabor_db`.`administrators` (`id_admin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dishes_food_outlets`
    FOREIGN KEY (`food_outlet`)
    REFERENCES `elcarrildelsabor_db`.`food_outlets` (`id_food_outlet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`promotions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`promotions` (
  `id_promotion` BIGINT NOT NULL AUTO_INCREMENT,
  `type` ENUM('COMBO', 'DISCOUNT') NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `minimum_amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `discount_percentage` DECIMAL(5,2) NULL DEFAULT NULL,
  `available` TINYINT NULL DEFAULT 1,
  `price` DECIMAL(10,2) NULL,
  `modify_by` BIGINT NULL,
  `img` MEDIUMBLOB NOT NULL,
  `food_outlet` BIGINT NOT NULL,
  PRIMARY KEY (`id_promotion`),
  INDEX `fk_promotions_admins_idx` (`modify_by` ASC) VISIBLE,
  INDEX `fk_promotions_food_outlets_idx` (`food_outlet` ASC) VISIBLE,
  CONSTRAINT `fk_promotions_admins`
    FOREIGN KEY (`modify_by`)
    REFERENCES `elcarrildelsabor_db`.`administrators` (`id_admin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_promotions_food_outlets`
    FOREIGN KEY (`food_outlet`)
    REFERENCES `elcarrildelsabor_db`.`food_outlets` (`id_food_outlet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`combos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`combos` (
  `id_combo` BIGINT NOT NULL AUTO_INCREMENT,
  `id_dish` BIGINT NOT NULL,
  `id_promotion` BIGINT NOT NULL,
  `quantity` SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_combo`),
  INDEX `fk_combos_promotions_idx` (`id_promotion` ASC) VISIBLE,
  INDEX `fk_combos_dishes_idx` (`id_dish` ASC) VISIBLE,
  CONSTRAINT `fk_combos_promotions`
    FOREIGN KEY (`id_promotion`)
    REFERENCES `elcarrildelsabor_db`.`promotions` (`id_promotion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_combos_dishes`
    FOREIGN KEY (`id_dish`)
    REFERENCES `elcarrildelsabor_db`.`dishes` (`id_dish`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`orders` (
  `id_order` BIGINT NOT NULL AUTO_INCREMENT,
  `customer` BIGINT NOT NULL,
  `delivery_address` VARCHAR(150) NOT NULL,
  `status` ENUM('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED', 'SUBMITTED', 'COMPLETED') NULL DEFAULT 'PENDING',
  `payment_method` ENUM('TRANSFER', 'CASH') NULL,
  `order_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_order`),
  INDEX `fk_orders_customers_idx` (`customer` ASC) VISIBLE,
  CONSTRAINT `fk_orders_customers`
    FOREIGN KEY (`customer`)
    REFERENCES `elcarrildelsabor_db`.`customers` (`id_customer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`order_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`order_details` (
  `id_order_detail` BIGINT NOT NULL AUTO_INCREMENT,
  `id_order` BIGINT NOT NULL,
  `id_dish` BIGINT NOT NULL,
  `promotion` BIGINT NOT NULL,
  `quantity` SMALLINT NULL DEFAULT 1,
  `details` VARCHAR(150) NULL,
  PRIMARY KEY (`id_order_detail`),
  INDEX `fk_orderdetails_orders_idx` (`id_order` ASC) VISIBLE,
  INDEX `fk_orderdetails_dishes_idx` (`id_dish` ASC) VISIBLE,
  INDEX `fk_orderdetails_promotions_idx` (`promotion` ASC) VISIBLE,
  CONSTRAINT `fk_orderdetails_orders`
    FOREIGN KEY (`id_order`)
    REFERENCES `elcarrildelsabor_db`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderdetails_dishes`
    FOREIGN KEY (`id_dish`)
    REFERENCES `elcarrildelsabor_db`.`dishes` (`id_dish`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderdetails_promotions`
    FOREIGN KEY (`promotion`)
    REFERENCES `elcarrildelsabor_db`.`promotions` (`id_promotion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`delivery_drivers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`delivery_drivers` (
  `id_delivery_driver` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `last_activity` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` BIGINT NOT NULL,
  `food_outlet` BIGINT NOT NULL,
  PRIMARY KEY (`id_delivery_driver`),
  INDEX `fk_deliverydrivers_users_idx` (`id_user` ASC) VISIBLE,
  INDEX `fk_delivery_drivers_food_outlets_idx` (`food_outlet` ASC) VISIBLE,
  CONSTRAINT `fk_deliverydrivers_users`
    FOREIGN KEY (`id_user`)
    REFERENCES `elcarrildelsabor_db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_delivery_drivers_food_outlets`
    FOREIGN KEY (`food_outlet`)
    REFERENCES `elcarrildelsabor_db`.`food_outlets` (`id_food_outlet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`assignments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`assignments` (
  `id_assignment` BIGINT NOT NULL AUTO_INCREMENT,
  `id_order` BIGINT NOT NULL,
  `assignment_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `delivery_assigned` BIGINT NOT NULL,
  `assigned_by` BIGINT NOT NULL,
  PRIMARY KEY (`id_assignment`),
  INDEX `fk_assignments_orders_idx` (`id_order` ASC) VISIBLE,
  INDEX `fk_assignments_deliverydrivers_idx` (`delivery_assigned` ASC) VISIBLE,
  INDEX `fk_assignments_administrators_idx` (`assigned_by` ASC) VISIBLE,
  CONSTRAINT `fk_assignments_orders`
    FOREIGN KEY (`id_order`)
    REFERENCES `elcarrildelsabor_db`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignments_deliverydrivers`
    FOREIGN KEY (`delivery_assigned`)
    REFERENCES `elcarrildelsabor_db`.`delivery_drivers` (`id_delivery_driver`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignments_administrators`
    FOREIGN KEY (`assigned_by`)
    REFERENCES `elcarrildelsabor_db`.`administrators` (`id_admin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `elcarrildelsabor_db` ;

-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`order_histories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`order_histories` (
  `id_order_history` BIGINT NOT NULL AUTO_INCREMENT,
  `id_order` BIGINT NOT NULL,
  `status` ENUM('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED', 'SUBMITTED', 'COMPLETED') NULL,
  `date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_order_history`),
  INDEX `fk_order_history_orders_idx` (`id_order` ASC) VISIBLE,
  CONSTRAINT `fk_order_history_orders`
    FOREIGN KEY (`id_order`)
    REFERENCES `elcarrildelsabor_db`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elcarrildelsabor_db`.`admin_registers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elcarrildelsabor_db`.`admin_registers` (
  `id_admin_register` BIGINT NOT NULL AUTO_INCREMENT,
  `action` VARCHAR(150) NOT NULL,
  `date` DATETIME NULL,
  `administrator` BIGINT NOT NULL,
  PRIMARY KEY (`id_admin_register`),
  INDEX `fk_admin_register_administrators_idx` (`administrator` ASC) VISIBLE,
  CONSTRAINT `fk_admin_register_administrators`
    FOREIGN KEY (`administrator`)
    REFERENCES `elcarrildelsabor_db`.`administrators` (`id_admin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
