(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d221bd2"],{cc40:function(e,r,t){"use strict";t.r(r);var l=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{staticClass:"app-container"},[t("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.formLoading,expression:"formLoading"}],ref:"form",attrs:{model:e.form,"label-width":"100px",rules:e.rules}},[t("el-form-item",{attrs:{label:"用户名：",prop:"userName",required:""}},[t("el-input",{model:{value:e.form.userName,callback:function(r){e.$set(e.form,"userName",r)},expression:"form.userName"}})],1),t("el-form-item",{attrs:{label:"密码：",required:""}},[t("el-input",{model:{value:e.form.password,callback:function(r){e.$set(e.form,"password",r)},expression:"form.password"}})],1),t("el-form-item",{attrs:{label:"真实姓名：",prop:"realName",required:""}},[t("el-input",{model:{value:e.form.realName,callback:function(r){e.$set(e.form,"realName",r)},expression:"form.realName"}})],1),t("el-form-item",{attrs:{label:"年龄："}},[t("el-input",{model:{value:e.form.age,callback:function(r){e.$set(e.form,"age",r)},expression:"form.age"}})],1),t("el-form-item",{attrs:{label:"性别："}},[t("el-select",{attrs:{placeholder:"性别",clearable:""},model:{value:e.form.sex,callback:function(r){e.$set(e.form,"sex",r)},expression:"form.sex"}},e._l(e.sexEnum,(function(e){return t("el-option",{key:e.key,attrs:{value:e.key,label:e.value}})})),1)],1),t("el-form-item",{attrs:{label:"出生日期："}},[t("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:e.form.birthDay,callback:function(r){e.$set(e.form,"birthDay",r)},expression:"form.birthDay"}})],1),t("el-form-item",{attrs:{label:"手机："}},[t("el-input",{model:{value:e.form.phone,callback:function(r){e.$set(e.form,"phone",r)},expression:"form.phone"}})],1),t("el-form-item",{attrs:{label:"年级：",prop:"userLevel",required:""}},[t("el-select",{attrs:{placeholder:"年级"},model:{value:e.form.userLevel,callback:function(r){e.$set(e.form,"userLevel",r)},expression:"form.userLevel"}},e._l(e.levelEnum,(function(e){return t("el-option",{key:e.key,attrs:{value:e.key,label:e.value}})})),1)],1),t("el-form-item",{attrs:{label:"状态：",required:""}},[t("el-select",{attrs:{placeholder:"状态"},model:{value:e.form.status,callback:function(r){e.$set(e.form,"status",r)},expression:"form.status"}},e._l(e.statusEnum,(function(e){return t("el-option",{key:e.key,attrs:{value:e.key,label:e.value}})})),1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("提交")]),t("el-button",{on:{click:e.resetForm}},[e._v("重置")])],1)],1)],1)},a=[],o=t("5530"),s=t("2f62"),n=t("c24f"),u={data:function(){return{form:{id:null,userName:"",password:"",realName:"",role:1,status:1,age:"",sex:"",birthDay:null,phone:null,userLevel:null},formLoading:!1,rules:{userName:[{required:!0,message:"请输入用户名",trigger:"blur"}],realName:[{required:!0,message:"请输入真实姓名",trigger:"blur"}],userLevel:[{required:!0,message:"请选择年级",trigger:"change"}]}}},created:function(){var e=this.$route.query.id,r=this;e&&0!==parseInt(e)&&(r.formLoading=!0,n["a"].selectUser(e).then((function(e){r.form=e.response,r.formLoading=!1})))},methods:Object(o["a"])({submitForm:function(){var e=this,r=this;this.$refs.form.validate((function(t){if(!t)return!1;e.formLoading=!0,n["a"].createUser(e.form).then((function(e){1===e.code?(r.$message.success(e.message),r.delCurrentView(r).then((function(){r.$router.push("/user/student/list")}))):(r.$message.error(e.message),r.formLoading=!1)})).catch((function(e){r.formLoading=!1}))}))},resetForm:function(){var e=this.form.id;this.$refs["form"].resetFields(),this.form={id:null,userName:"",password:"",realName:"",role:1,status:1,age:"",sex:"",birthDay:null,phone:null,userLevel:null},this.form.id=e}},Object(s["b"])("tagsView",{delCurrentView:"delCurrentView"})),computed:Object(o["a"])(Object(o["a"])({},Object(s["c"])("enumItem",["enumFormat"])),Object(s["e"])("enumItem",{sexEnum:function(e){return e.user.sexEnum},roleEnum:function(e){return e.user.roleEnum},statusEnum:function(e){return e.user.statusEnum},levelEnum:function(e){return e.user.levelEnum}}))},m=u,i=t("2877"),f=Object(i["a"])(m,l,a,!1,null,null,null);r["default"]=f.exports}}]);