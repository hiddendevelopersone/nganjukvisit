

# Nganjuk Visit (android)

Nganjuk Visit is a mobile Android application designed to help tourists explore the beauty of Nganjuk Regency with ease. This app offers a wide range of features to assist users in discovering the best tourist destinations, viewing detailed information about each location, and receiving recommendations for local cuisine, hotels, and exciting events around Nganjuk.
This application serves as a digital solution to enhance the tourist experience by providing fast, accurate, and user-friendly access to information. It also supports local tourism promotion and contributes to the growth of the tourism-based economy.

![Logo](https://i.postimg.cc/sxycHRj1/newlogo-nganjukvisit.png)


## Screenshots

Home Page             |  List Wisata Page
:-------------------------:|:-------------------------:
![](https://i.postimg.cc/dVBLGPX4/1-homepage-1.jpg)  |  ![](https://i.postimg.cc/wMLBvSL5/2-listwisata.jpg)

Detail Wisata Page (1)             |  Detail Wisata Page (2)
:-------------------------:|:-------------------------:
![](https://i.postimg.cc/448CHJXW/detailwisata2.jpg)  |  ![](https://i.postimg.cc/dtDb8z1w/detailwisata3.jpg)

Detail Wisata Page (3)             |  List Kuliner Page
:-------------------------:|:-------------------------:
![](https://i.postimg.cc/dtxL6PjB/detailwisata4.jpg)  |  ![](https://i.postimg.cc/MKvGv3Jj/4-listkuliner.jpg)

List Penginapan Page             |  List Event Page
:-------------------------:|:-------------------------:
![](https://i.postimg.cc/rwG2m5sr/5-listpenginapan.jpg)  |  ![](https://i.postimg.cc/pVgd5YWG/6-listevent.jpg)


## Tech Stack 🛠️  

**Main App:** Java (Native), XML.  

**API Integration:** Retrofit for connecting with backend services.  

**Backend:** Native PHP API endpoints from website.  

**Database:** MySQL (hosted on XAMPP).  

## Features ✨  
- Discover top tourist attractions in Nganjuk.  
- View detailed information about each destination.  
- Get recommendations for local food, hotels, and events.
- Minimap Feature in destination.  
- Feedback Feature (comment section).
- Favorite Feature (Liked & Store it to Favorite Page) 

## Run Locally

1. Clone the project

```bash
  git clone https://github.com/hiddendevelopersone/nganjukvisit.git
```

2. Move the website files to XAMPP's htdocs folder.

3. Start XAMPP, enable Apache and MySQL.

4. Open phpMyAdmin and import nganjuk_visit.sql to create the database.

5. Configure the backend API URL in Client.java
```java
  public static final String BASE_URL = "http://localhost/nganjukvisit/";
```

6. Run Application

## Authors

- [@southampere28](https://www.github.com/southampere28)
