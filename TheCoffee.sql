create database caphe1
go
use caphe1
go
create table ProductType
(
	IDType varchar(10) primary key,
	TypeName nvarchar(50),
	Size nvarchar(10)
)
go
create table Product
(
	IDProduct varchar(20) primary key,
	ProductName nvarchar(100),
	IDType varchar(10) FOREIGN KEY REFERENCES ProductType(IDType),
	Price int
)
go
create table Administrator(
	Username varchar(50) Primary key,
	[Password] varchar(20)
)
go
create table Employee(
	UsernameEmp varchar(50) Primary key,
	[Password] varchar(20),
	NameEmp nvarchar(50),
	Gender nvarchar(10),
	Birthday varchar(20),
	Phone varchar(20),
	Email varchar(50),
	[Address] nvarchar(Max),
	Hinh varchar(50)
)
go
Create Table Revenue(
	IDRevenue int Identity(1,1) Primary key,
	[Date] date ,
	[Money] int
)
go
create table Customer(
	IDCus int Identity(100000,1) Primary key,
	IdentityCard varchar(20) not null UNIQUE,
	CusName nvarchar(50),
	[DateAdd] varchar(20),
	Phone varchar(20),
	Email varchar(50),
	Quantity int,
	Discount int
)
go
create table Promotions(
	IDPromo int Identity(1,1) Primary key,
	NamePromo nvarchar(50) not null UNIQUE,
	DiscountPromo int,
	StartPromo varchar(20),
	EndPromo varchar(20),
	[Description] nvarchar(Max)
)
go
Create Table [Order](
	IDOrder  int Identity(1,1) Primary key,
	DateOrder varchar(20),
	TimeOrder varchar(20),
	UsernameEmp varchar(50) foreign key REFERENCES Employee(UsernameEmp)
)
go
Create Table OrderDetails(
	IDOrder  int  foreign key REFERENCES [Order](IDOrder),
	IDProduct varchar(20) foreign key REFERENCES Product(IDProduct),
	CusName nvarchar(50),
	Quantity int,
	NamePromo nvarchar(50),
	Constraint PK_OrderDetails Primary key (IDOrder,IDProduct)
)
go
--Insert 1 Administrators
Insert into Administrator values('admin','admin')

--Insert 4 Employees information
Insert into Employee values('hoanghung','123456',N'Võ Ngọc Hoàng Hưng',N'Nam','28/04/2000','0337645534','hoanghungfcb28@gmail.com',N'Quận 12, TP Hồ Chí Minh','h1.PNG')
Insert into Employee values('duytan','123456',N'Trần Duy Tân',N'Nam','02/03/2000','0124221177','duytan@gmail.com',N'Tphcm','h2.PNG')
Insert into Employee values('nhatkhanh','123456',N'Huỳnh Nhật Khánh',N'Nam','22/08/2001','0932212333','khanhnhat@gmail.com',N'15 Tân Bình','h3.PNG')
Insert into Employee values('tuanhau','123456',N'Nguyễn Tuấn Hậu',N'Nam','15/02/2001','01227971919','tuanhau@gmail.com',N'9 Bình Tân ','h4.PNG')
Insert into Employee values('tuanmanh','123456',N'Nguyễn Tuấn Manh',N'Nam','01/05/1997','0909123456','tuanmanh@gmail.com',N'12 Lê Hồng Phong','h5.PNG')
Insert into Employee values('huuphat','123456',N'Mai Hữu Phát',N'Nam','01/05/1994','0909123456','huuphat@gmail.com',N'12 Lê Hồng Phong','h6.PNG')

--Inser 7 ProductTypes
Insert into ProductType values('T01',N'Cà phê',N'Nhỏ')
Insert into ProductType values('T02',N'Cà phê',N'Vừa')
Insert into ProductType values('T03',N'Cà phê',N'Lớn')
Insert into ProductType values('T04',N'Nước trái cây',N'Nhỏ')
Insert into ProductType values('T05',N'Nước trái cây',N'Vừa')
Insert into ProductType values('T06',N'Nước trái cây',N'Lớn')

--Insert 20 Products
Insert into Product values('CF01', N'Cà phê đá', 'T01', 20000)
Insert into Product values('CF02', N'Cà phê đá', 'T02', 25000)
Insert into Product values('CF03', N'Cà phê đá', 'T03', 30000)
Insert into Product values('CF04', N'Cà phê sữa đá', 'T01', 25000)
Insert into Product values('CF05', N'Cà phê sữa đá', 'T02', 30000)
Insert into Product values('CF06', N'Cà phê sữa đá', 'T03', 35000)
Insert into Product values('ES01', N'ESPRESSO MILK', 'T01', 30000)
Insert into Product values('ES02', N'ESPRESSO MILK', 'T02', 40000)
Insert into Product values('ES03', N'ESPRESSO MILK', 'T03', 50000)
Insert into Product values('CA01', N'CAPPUCCINO', 'T01', 42000)
Insert into Product values('CA02', N'CAPPUCCINO', 'T02', 52000)
Insert into Product values('CA03', N'CAPPUCCINO', 'T03', 62000)
Insert into Product values('TD01', N'Trà đào', 'T04', 40000)
Insert into Product values('TD02', N'Trà đào', 'T05', 50000)
Insert into Product values('TD03', N'Trà đào', 'T06', 60000)
Insert into Product values('CE01', N'Cam ép', 'T04', 35000)
Insert into Product values('CE02', N'Cam ép', 'T05', 40000)
Insert into Product values('ST01', N'Sinh tố thập cẩm', 'T04', 50000)
Insert into Product values('ST02', N'Sinh tố thập cẩm', 'T05', 60000)

--Insert 9 Revenues
Insert into Revenue values('10/01/2020',179000)
Insert into Revenue values('11/13/2020',50000)
Insert into Revenue values('09/03/2020',206000)
Insert into Revenue values('08/04/2020',240000)
Insert into Revenue values('07/05/2020',126000)
Insert into Revenue values('06/06/2020',52000)
Insert into Revenue values('05/07/2020',45000)
Insert into Revenue values('04/08/2020',45000)
Insert into Revenue values('03/09/2020',110000)
Insert into Revenue values('02/10/2020',110000)

--Insert 4 Customers
Insert into Customer values('122261551',N'Nguyễn Nhật Minh','17/11/2020','01212692802','minhnhat@gmail.com',20,10)
Insert into Customer values('122261552',N'Lê Ái Vinh','23/11/2020','01212692802','aivinh@gmail.com',25,10)
Insert into Customer values('122261553',N'Nguyễn Hoàng Long','20/11/2020','01212692802','nguyenlong@gmail.com',19,5)
Insert into Customer values('122261554',N'Nguyễn Văn Trí','11/11/2020','01212692802','tri@gmail.com',10,5)

--Insert 2 Promotions
Insert into Promotions values(N'Thẻ sinh viên',10,'2020/11/01','2017/12/30',N'Có thẻ sinh viên')

--Insert 6 Order

--Insert 10 OrderDetails
