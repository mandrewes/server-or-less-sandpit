import React from 'react';
import { ButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

export default class Examples extends React.Component {
  constructor(props) {
    super(props);
    this.cb = props.cb;
    this.toggle = this.toggle.bind(this);
    this.state = {
      dropdownOpen: false
    };

    this.byGroupId = this.byGroupId.bind(this);
    this.byAccountId = this.byAccountId.bind(this);
    this.byFolder = this.byFolder.bind(this);
    this.byFolderWithNonIndexedFields = this.byFolderWithNonIndexedFields.bind(this);
    this.byAccountWithNonIndexedFields = this.byAccountWithNonIndexedFields.bind(this);
    this.byGroupWithNonIndexedFields = this.byGroupWithNonIndexedFields.bind(this);

  }

  toggle() {
    this.setState({
      dropdownOpen: !this.state.dropdownOpen
    });
  }

  byGroupId() {
    this.cb( {
         accountId: "",
         customerId: "",
         groupId: "TX1210150419AJWA1",
         folder: "",
         freeFormNameA: "",
         freeFormValueA: "",
         freeFormNameB: "",
         freeFormValueB: ""
    });
  }

  byFolder() {
    this.cb( {
         accountId: "",
         customerId: "",
         groupId: "",
         folder: "/cowmilk/tifraisdeshauts",
         freeFormNameA: "",
         freeFormValueA: "",
         freeFormNameB: "",
         freeFormValueB: ""
     });
  }

  byAccountId() {
    this.cb( {
         accountId: "TX1506220421PEOH123-35-36yi7RoZQd",
         customerId: "",
         groupId: "",
         folder: "",
         freeFormNameA: "",
         freeFormValueA: "",
         freeFormNameB: "",
         freeFormValueB: "",
     });
  }

  byFolderWithNonIndexedFields() {
    this.cb( {
         accountId: "",
         customerId: "",
         groupId: "",
         folder: "/cowmilk/tifraisdeshauts",
         freeFormNameA: "high_level_category",
         freeFormValueA: "Apple Juice",
         freeFormNameB: "",
         freeFormValueB: ""
     });
  }

  byGroupWithNonIndexedFields() {
    this.cb( {
         accountId: "",
         customerId: "",
         groupId: "TX1210150419AJWA1",
         folder: "",
         freeFormNameA: "low_level_category",
         freeFormValueA: "0.012",
         freeFormNameB: "",
         freeFormValueB: ""
     });
  }

  byAccountWithNonIndexedFields() {
    this.cb( {
         accountId: "TX1210150419AJWA100-36-56",
         customerId: "",
         groupId: "",
         folder: "/cowmilk/tifraisdeshauts",
         freeFormNameA: "i",
         freeFormValueA: "90030",
         freeFormNameB: "",
         freeFormValueB: ""
     });
  }


  render() {
    return (
      <ButtonDropdown isOpen={this.state.dropdownOpen} toggle={this.toggle} size="sm">
        <DropdownToggle caret>
          Example Searches
        </DropdownToggle>
        <DropdownMenu>
          <DropdownItem onClick={this.byAccountId} >By Account ID</DropdownItem>
          <DropdownItem onClick={this.byGroupId} > By Group ID</DropdownItem>
          <DropdownItem onClick={this.byFolder} >By Folder</DropdownItem>
          <DropdownItem divider />
          <DropdownItem onClick={this.byAccountWithNonIndexedFields} >By Account with non indexed fields</DropdownItem>
          <DropdownItem onClick={this.byGroupWithNonIndexedFields} >By Group with non indexed fields</DropdownItem>
          <DropdownItem onClick={this.byFolderWithNonIndexedFields} >By Folder with non indexed fields</DropdownItem>
        </DropdownMenu>
      </ButtonDropdown>
    );
  }
}