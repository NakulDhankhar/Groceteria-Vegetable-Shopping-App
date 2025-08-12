import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GroceteriaService {
  url: string = environment.apiUrl;


  //CATEGORY SECTION - Updated to match backend enum values
  category: any=[{
    name: "VEGETABLES", value: "VEGETABLES"
  }, 
  {
    name: "FRUITS" , value: "FRUITS"
  },
  {
    name: "DAIRYPRODUCTS", value: "DAIRYPRODUCTS"
  },
  {
    name: "MEAT", value: "MEAT"
  },
  {
    name: "GRAINSANDOILS", value: "GRAINSANDOILS"
  },
  {
    name: "SPICESANDSEASONINGS", value: "SPICESANDSEASONINGS"
  },
  {
    name: "BAKINGINGREDIENTS", value: "BAKINGINGREDIENTS"
  },
  {
    name: "CONDIMENTS", value: "CONDIMENTS"
  },
  {
    name: "SNACKS", value: "SNACKS"
  },
  {
    name: "SKINCARE", value: "SKINCARE"
  }]

  constructor(
    private http: HttpClient,
    private route: Router
  ) { }


  /*customer Registeration*/
  signUp(body: any):Observable<any>{
    return this.http.post(this.url + "/users/register", body);
  }

  /*customer login*/
  userSignIn(body: any): Observable<any>{
    return this.http.post(this.url + "/users/login", body);
  }

  // once we logged in that time we are storing customer id into token
  storeUserAuthorization(token: string): void {
    localStorage.setItem("token", token);
  }

  getUserAuthorization(): any {
    const token = localStorage.getItem("token");
    return token;
  }

  storeUserName(name: string): void {
    localStorage.setItem("userName", name);
  }

  getUserName(): any {
    const name = localStorage.getItem('userName');
    return name;
  }

  userLogout(): void {
    localStorage.clear();
    this.route.navigate(['']);
  }


   //admin login
   adminSignIn(body: any): Observable<any> {
    // return this.http.post(this.url + "/api/admin/login", body);
    return this.http.post(this.url + "/users/login", body);
  }
  storeAdminAuthorization(token: string): void {
    localStorage.setItem("admin", token);
  }
  getAdminAuthorization(): any {
    const token = localStorage.getItem("admin");
    return token;
  }

  storeAdminUserName(name: string): void {
    localStorage.setItem("adminName", name);
  }

  getAdminName(): any {
    const name = localStorage.getItem("adminName");
    return name;
  }

  adminLogout(): void {
    localStorage.clear();
    this.route.navigate(['/']);
  }

  // this is to get username in admin.home.html part via admin.home.ts
  isAdminLoginPresent(): void {
    if (this.getAdminAuthorization() === null) {
      this.route.navigate(['/admin-login']);
    }
  }
  isUserLoginPresent(): void {
    if (this.getUserAuthorization() === null) {
      this.route.navigate(['/user-login']);
    }
  }


  //user-role
  storeUserRole(role: string): void {
    localStorage.setItem("role", role);
  }

  getUserRole(): any {
    const role = localStorage.getItem("role");
    return role;
  }


  //METHODS 
  //@RequestMapping("/items")
  //@PostMapping
  addItem(body: any, vendorId: any): Observable<any>{
    return this.http.post(this.url + "/items?vendorId=" + vendorId, body);
  }
  //@GetMapping
  getItemlist(): Observable<any>{
    return this.http.get(this.url + "/items");
  }

  //@DeleteMapping("{itemId}")
  deleteItem(id: any): Observable<any>{
    return this.http.delete(this.url + "/items/" +id);
  }

  //@GetMapping("/{itemId}")
  getItemById(id: any): Observable<any>{
    return this.http.get(this.url + "/items/"+id);
  }

  //@PutMapping("{itemId}")
  editItem(body: any, id: any): Observable<any>{
    return this.http.put(this.url + "/items/" +id, body);
  }


  //CART PART
  //@RequestMapping("/cart")
  //@PostMapping
  addToCart(body: any, itemId: any, userId: any): Observable<any>{
    return this.http.post(this.url+"/cart?itemId="+itemId+"&userId="+userId, body);
  }


  //@RequestMapping("/users")
  //@GetMapping("/{userId}")
  getUserById(id:any):Observable<any> {
    return this.http.get(this.url + "/users/"+id);
  }
  
  //@GetMapping
  cartList():Observable<any>{
    return this.http.get(this.url+"/cart");
  }

  //@RequestMapping("/orders")
  //@PostMapping
  placeOrder(body:any):Observable<any> {
    return this.http.post(this.url + "/orders", body);
  }

  //@DeleteMapping("/{cartId}")
  deleteCart(id :any):Observable<any> {
    return this.http.delete(`${this.url}/cart/${id}`);
  }

  orderList(id:any):Observable<any>{
    return this.http.get(this.url+"/orders/user/"+id);
  }

  getCategoryList(): any {
    return this.category;
  }

  //PAYMENT PART
  //@RequestMapping("/payments")
  //@PostMapping
  addPayment(body:any):Observable<any> {
    return this.http.post(this.url + "/payments", body);
  }

  //@PostMapping("/forgot-password")
  forgotPassword(body: any):Observable<any> {
    return this.http.post(this.url + "/users/forgot-password", body);
  }

  updateUserInformation(body: any):Observable<any> {
    return this.http.put(this.url + "/users/"+body?.userId, body);
  }

  changePassword(uid: any,password:any):Observable<any> {
    return this.http.post(this.url + "/users/"+uid+"/"+password,{});
  }

  //@GetMapping("/category/{category}/paged")
  getItemByCategory(cid: any, pageNo: any, pageSize: any):Observable<any>{
    return this.http.get(this.url+"/items/category/" + cid + "/paged?pageNo=" + pageNo + "&pageSize=" + pageSize);
  }
  
  getAllItems(pageNo: any, pageSize: any):Observable<any>{
    return this.http.get(this.url+"/items/paged?pageNo=" + pageNo + "&pageSize=" + pageSize);
  }

  // Get all items without pagination (for admin item list)
  getAllItemsList():Observable<any>{
    return this.http.get(this.url+"/items");
  }

  getAllorderList():Observable<any>{
    return this.http.get(this.url+"/orders");
  }

  //@PostMapping
  placeOrderItem(cid:any, body:any):Observable<any>{
    return this.http.post(this.url + "/orders?userId="+cid, body);
  }

  //@PostMapping
  addPaymentOfOrder(body: any):Observable<any> {
    return this.http.post(this.url + "/payments", body);
  }

  //@GetMapping("/search")
  searchItemByName(keyword: any, pageNo: any, pageSize: any):Observable<any> {
    return this.http.get(this.url + `/items/search?keyword=${keyword}&pageNo=${pageNo}&pageSize=${pageSize}`);
  }

  deleteCartByQuanity(id :any, quantity: any):Observable<any> {
    return this.http.put(`${this.url}/cart/${id}/quantity?quantity=${quantity}`, {});
  }
}
