import { Component, OnInit } from '@angular/core';
import { Item } from '../../model/item.model';

import { Router } from '@angular/router';
import { Observable, take } from 'rxjs';
import { GroceteriaService } from '../../../groceteria.service';

@Component({
  selector: 'app-admin-itemlist',
  templateUrl: './admin-itemlist.component.html',
  styleUrls: ['./admin-itemlist.component.css']
})
export class AdminItemlistComponent {
  itemList: Array<Item> = [];
  getCategoryList: any[] = [];
  category: any = 100;
  allItemList: Array<Item> = [];
  offset: number = 0;
  pageSize: number = 5;
  totalItem: number = 1;

  constructor(
    private gservice: GroceteriaService,
    private router: Router
  )
  {
    this.gservice.isAdminLoginPresent();
    this.getItemList(true);

  }

  ngOnInit(): void{
    this.getCategoryList = this.gservice.getCategoryList();
    console.log("********",this.getCategoryList);
  }

  getItemList(isAllItem: boolean = false): void{
    let item: any;
    if (isAllItem) {
      item = this.gservice.getAllItemsList();
    } else {
      item = this.gservice.getItemByCategory(this.category, this.offset -1 < 0 ? 0 : this.offset -1, this.pageSize);
    }

    item.pipe(take(1)).subscribe((res: any) => {
      if(res && Array.isArray(res)){
        this.itemList = res;
        this.allItemList = res;
        this.totalItem = res.length;
      } else if(res && res?.content && Array.isArray(res?.content)){
        // Handle paginated response
        this.itemList = res?.content;
        this.allItemList = res?.content;
        this.totalItem = res?.totalElements || res?.content.length;
      }
    }, (err: any) => {
      console.log("Error", err);
      console.log("It is not working");
    });
  }

 
  //delItem
  delItem(item: Item): void{
    this.gservice.deleteItem(item?.itemId).pipe(take(1)).subscribe(
      (res: any) => {
        alert("Item deleted Successfully");
        this.getItemList(this.category === 100 || this.category === "100");
      }, err => {
        console.log("Error");
      }
    );
  }

  //EditItem
  editItem(item: Item): void{
    this.router.navigate(['/admin/additem'],{
      queryParams: {
        id: item?.itemId
      }
    });

  }

  //getItemByCategory
  getItemByCategory(): void{
    this.offset = 0;
    this.totalItem = 1;
    if(this.category === "100"){
      this.getItemList(true);
    }else{
      this.getItemList(false);
    }

  }

  onNextPageClick(pageOffSet: any): void{
    this.offset = pageOffSet;
    this.getItemList(this.category === 100 || this.category === "100");
  }

  onPreviousPageClick(pageOffSet: any): void{
    this.offset -=1;
    this.getItemList(this.category === 100 || this.category === "100");
  }

  onFirstPageClick(pageOffSet: any): void{
    this.offset = 0;
    this.getItemList(this.category === 100 || this.category === "100");
  }

  onLastPageClick(pageOffSet: any): void{
    const lastPage = Math.ceil(this.totalItem / this.pageSize);
    this.offset = lastPage;
    this.getItemList(this.category === 100 || this.category === "100");
  }
  


}
