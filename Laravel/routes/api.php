<?php

use Illuminate\Http\Request;

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\MaterielController;
use App\Http\Controllers\DemandeMaterielController;
use App\Http\Controllers\CategorieMaterielController;
use App\Http\Controllers\DemandeCreationClubController;

use App\Http\Controllers\AboutController;
use App\Http\Controllers\BoardController;
use App\Http\Controllers\HeadersController;
use App\Http\Controllers\ProjectsController;
use App\Http\Controllers\ActivitiesController;
use App\Http\Controllers\MemberController;


/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
Route::group(['prefix'=>'/dcc'],function(){
    Route::get('/',[DemandeCreationClubController::class,'show']);
    Route::get('/{id}',[DemandeCreationClubController::class,'showMyDemandes']);
    Route::post('/',[DemandeCreationClubController::class,'create']);
    Route::put('/{id}',[DemandeCreationClubController::class,'update']);
    Route::delete('/{id}',[DemandeCreationClubController::class,'delete']);
    Route::put('/a/{id}',[DemandeCreationClubController::class,'accept']);
    Route::put('/d/{id}',[DemandeCreationClubController::class,'decline']);
});
Route::group(['prefix'=>'/dm'],function(){
    Route::get('/',[DemandeMaterielController::class,'show']);
    // Route::get('/{id}',[DemandeMaterielController::class,'showMyDemandes']);
    Route::post('/',[DemandeMaterielController::class,'create']);
    Route::put('/{id}',[DemandeMaterielController::class,'update']);
    Route::delete('/{id}',[DemandeMaterielController::class,'delete']);
});
Route::group(['prefix'=>'/m'],function(){
    Route::get('/M/{id}',[MaterielController::class,'show']);
    Route::get('/{id}',[MaterielController::class,'showMaterialsOfCategory']);
    Route::post('/{idM}/{idD}',[MaterielController::class,'addMateriel']);
    Route::put('/Q/{idM}/{idD}',[MaterielController::class,'setQuantity']);
    Route::delete('/{idM}/{idD}',[MaterielController::class,'deleteMateriel']);
    Route::put('/{idM}/{idD}',[MaterielController::class,'update']);
    Route::delete('/{id}',[MaterielController::class,'delete']);
});
Route::group(['prefix'=>'/c'],function(){
    Route::get('/',[CategorieMaterielController::class,'show']);
});

Route::get('{idClub}/header/getAll',[HeadersController::class,'getHeaders']);
Route::post('{idClub}/header/create',[HeadersController::class,'createHeader']);
Route::delete('{idClub}/header/delete/{id}',[HeadersController::class,'deleteHeader']);
Route::put('{idClub}/header/update/{id}',[HeadersController::class,'updateHeader']);


Route::get('{idClub}/about/getAll',[AboutController::class,'getAbout']);
Route::post('{idClub}/about/create',[AboutController::class,'createAbout']);
Route::delete('{idClub}/about/delete/{id}',[AboutController::class,'deleteAbout']);
Route::put('{idClub}/about/update/{id}',[AboutController::class,'updateAbout']);

Route::get('{idClub}/activities/getAll',[ActivitiesController::class,'getActivities']);
Route::post('{idClub}/activities/create',[ActivitiesController::class,'createActivities']);
Route::delete('{idClub}/activities/delete/{id}',[ActivitiesController::class,'deleteActivity']);
Route::put('{idClub}/activities/update/{id}',[ActivitiesController::class,'updateActivity']);

Route::get('{idClub}/boards/getAll',[BoardController::class,'getBoard']);
Route::post('{idClub}/boards/create',[BoardController::class,'createBoard']);
Route::delete('{idClub}/boards/delete/{id}',[BoardController::class,'deleteBoard']);
Route::put('{idClub}/boards/update/{id}',[BoardController::class,'updateBoard']);

Route::get('{idClub}/projects/getAll',[ProjectsController::class,'getProjects']);
Route::post('{idClub}/projects/create',[ProjectsController::class,'createProject']);
Route::delete('{idClub}/projects/delete/{id}',[ProjectsController::class,'deleteProject']);
Route::put('{idClub}/projects/update/{id}',[ProjectsController::class,'updateProject']);

Route::group(['prefix'=>'/members'],function(){
    Route::get('/{id}',[MemberController::class,'getMembers']);
    Route::delete('/{id}',[MemberController::class,'deleteMember']);
});


