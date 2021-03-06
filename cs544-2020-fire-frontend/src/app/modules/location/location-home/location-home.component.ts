import { Component, OnInit } from '@angular/core';
import Location from "../../../domain/models/location.model";
import {FireserviceService} from "../../../domain/services/testservice/fireservice.service";
import {CourseClientService, LocationClientService} from "../../../domain/core/http";
import {AuthService} from "../../../domain/core/security/auth.service";
import {UserRoleEnum} from "../../../domain/enums";

@Component({
  selector: 'fire-location-home',
  templateUrl: './location-home.component.html',
  styleUrls: ['./location-home.component.less']
})
export class LocationHomeComponent implements OnInit {

  DEFAULT_EMPTY_LOCATION: Location = {
    id: undefined,
    description: '',
  };

  selectedTab = 0;
  isAdmin: boolean;
  currentLocation: Location;
  locations: Location[];

  constructor(private fireService: FireserviceService,
              private locationClientService: LocationClientService,
              public auth: AuthService) {
    this.isAdmin = auth.isActiveRole([UserRoleEnum.ADMIN])
  }

  ngOnInit() {
    this.loadListLocation();
  }

  onEditLocation(location: Location) {
    this.currentLocation = location;
    this.selectedTab = 1;
  }

  onAddLocation() {
    this.currentLocation = {
      ...this.DEFAULT_EMPTY_LOCATION,
    };
    this.selectedTab = 1;
  }

  saveLocation(location: Location) {
    this.locationClientService.addLocation$(location).subscribe(location => {
      console.log(location);
      this.selectListLocation();
    })
  }

  deleteLocation(location: Location) {
    this.locationClientService.deleteLocation$(location.id).subscribe(() => {
      this.selectListLocation();
    });
  }

  updateLocation(location: Location) {
    this.locationClientService.modifyLocation$(location).subscribe(() => {
      this.selectListLocation();
    });
  }

  selectListLocation() {
    this.currentLocation = {
      ...this.DEFAULT_EMPTY_LOCATION,
    };
    this.loadListLocation();
  }

  private loadListLocation() {
    this.locationClientService.getAllLocations$().subscribe(locations => {
      this.locations = locations;
      this.selectedTab = 0;
    })
  }
}
