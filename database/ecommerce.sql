#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Table: Product
#------------------------------------------------------------

CREATE TABLE Product(
        id_product Int  Auto_increment  NOT NULL ,
        name       Varchar (255) NOT NULL ,
        Qte        Varchar (255) NOT NULL ,
        Category   Varchar (255) NOT NULL ,
        price      Float NOT NULL,
        photo   Varchar (255) NOT NULL ,
        description Varchar(255) NOT NULL
        ,CONSTRAINT Product_PK PRIMARY KEY (id_product)
)ENGINE=InnoDB;




#------------------------------------------------------------
# Table: admin
#------------------------------------------------------------

CREATE TABLE admin(
        id_admin Int NOT NULL AUTO_INCREMENT,
        email    Varchar (255) NOT NULL ,
        password Varchar (255) NOT NULL
        ,CONSTRAINT admin_PK PRIMARY KEY (id_admin)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: g�rer
#------------------------------------------------------------

CREATE TABLE gerer(
        id_product Int NOT NULL ,
        id_user    Int NOT NULL ,
        id_admin   Int NOT NULL
        ,CONSTRAINT gerer_PK PRIMARY KEY (id_product,id_user,id_admin)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        id_client Int NOT NULL AUTO_INCREMENT,
        name      Varchar (255) NOT NULL ,
        firstName Varchar (255) NOT NULL ,
        street    Varchar (255) NOT NULL ,
        town      Varchar (255) NOT NULL ,
        postal    Int NOT NULL ,
        num_tel   Varchar (255) NOT NULL ,
        email     Varchar (255) NOT NULL ,
        password  Varchar (255) NOT NULL 
        ,CONSTRAINT Client_PK PRIMARY KEY (id_client)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Facture 
#------------------------------------------------------------

CREATE TABLE Facture(
        id_facture Int  Auto_increment  NOT NULL ,
        prix_total Varchar (255) NOT NULL ,
        id_panier  Int NOT NULL
        ,CONSTRAINT Facture_PK PRIMARY KEY (id_facture)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Panier
#------------------------------------------------------------

CREATE TABLE Panier(
        id_panier  Int  Auto_increment  NOT NULL ,
        prix_total Varchar (255) NOT NULL ,
        id_user    Int NOT NULL ,
        id_client  Int NOT NULL ,
        id_facture Int NOT NULL
        ,CONSTRAINT Panier_PK PRIMARY KEY (id_panier)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Commander
#------------------------------------------------------------

CREATE TABLE Commander(
        id_panier  Int NOT NULL ,
        id_product Int NOT NULL ,
        qte_cmd    Int NOT NULL ,
        date_cmd   Date NOT NULL
        ,CONSTRAINT Commander_PK PRIMARY KEY (id_panier,id_product)
)ENGINE=InnoDB;




ALTER TABLE gerer
        ADD CONSTRAINT gerer_Product0_FK
        FOREIGN KEY (id_product)
        REFERENCES Product(id_product);

ALTER TABLE gerer
        ADD CONSTRAINT gerer_admin1_FK
        FOREIGN KEY (id_admin)
        REFERENCES admin(id_admin);


ALTER TABLE Facture
        ADD CONSTRAINT Facture_Panier0_FK
        FOREIGN KEY (id_panier)
        REFERENCES Panier(id_panier);

ALTER TABLE Facture 
        ADD CONSTRAINT Facture_Panier0_AK 
        UNIQUE (id_panier);

ALTER TABLE Panier
        ADD CONSTRAINT Panier_Client0_FK
        FOREIGN KEY (id_client)
        REFERENCES Client(id_client);




