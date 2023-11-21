# jajanmania-mobile

## Jajan Mania - Vendor

### Features available in the app
- Login
- Register
- Manage Toko
- Tambah Produk/Jajanan
- Edit Produk/Jajanan
- Hapus Produk/Jajanan
- Melihat Daftar Produk/Jajanan
- Withdraw/Transfer Bank
- Melihat Profile
- Update Profile
- Melihat History Transaksi
- Melihat Detail Transaksi

### Code architecture
Clean architecture is a software design theory for Android that divides a design's components into ring tiers. The main goal of clean architecture is to allow developers to structure their code in a way that incorporates business logic while keeping it separate from the delivery mechanism.
Here are some principles of clean architecture: 
Separation of concerns
Logical distribution of code
Use of abstractions
Creation of a central core that is independent of external dependencies
![image](https://github.com/Ferosburn/jajanmania-mobile/assets/111162360/ef79a452-9db0-4661-b802-c97c1e544e6c)

### Dependencies
- kotlin:1.8.10
- compose-bom:2023.06.01
- material-icons-extended:1.5.3
- accompanist-systemuicontroller:0.33.2-alpha
- navigation-compose:2.7.4
- lifecycle-runtime-compose:2.6.2
- coil-compose:2.4.0
- koin-bom:3.5.1
- koin-core
- koin-android
- koin-androidx-compose
- sheets-compose-dialogs:1.2.0

### List of endpoints called and the rationale
- [POST] /api/v1/authentications/vendors/login?method=email_and_password
- [POST] /api/v1/authentications/vendors/register?method=email_and_password
- [POST] /api/v1/authentications/vendors/refreshes/access-token
- [GET] /api/v1/vendors/:id
- [POST] /api/v1/jajan-items
- [POST] /api/v1/transactions/checkout
- [PATCH]/api/v1/jajan-items/:id
- [GET] /api/v1/transaction-histories?page_number=1&page_size=10&where=encodeUriComponent(whereInputObject)&include=encodeUriComponent(includeInputObject)
- [GET] /api/v1/transaction-histories/:id
- [GET] /api/v1/vendors/:id
- [GET] /api/v1/categories

### Tech Stack
- Kotlin
- Jetpack Compose
- Material Design 3
- Navigation Component
- Koin

### How to install and run the app
1. Clone the repository
`git clone https://github.com/Ferosburn/jajanmania-mobile.git`
2. Extract the project
3. Open project in android studio
4. Run project
