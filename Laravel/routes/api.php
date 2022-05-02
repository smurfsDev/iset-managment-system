<?php

use Illuminate\Http\Request;

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\MaterielController;
use App\Http\Controllers\DemandeMaterielController;
use App\Http\Controllers\CategorieMaterielController;
use App\Http\Controllers\DemandeCreationClubController;

use App\Http\Controllers\club\pageClub\AboutController;
use App\Http\Controllers\club\pageClub\BoardController;
use App\Http\Controllers\club\pageClub\HeadersController;
use App\Http\Controllers\club\pageClub\ProjectsController;
use App\Http\Controllers\club\pageClub\ActivitiesController;

use App\Http\Controllers\API\AuthController;
use App\Http\Controllers\club\ClubController;
use App\Http\Controllers\club\MemberController;
use App\Http\Controllers\SalleController;
use App\Http\Controllers\DemandeSalleController;
use App\Http\Controllers\DepartementController;


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

Route::post('login', [AuthController::class, 'signin']);
Route::post('register', [AuthController::class, 'signup']);

Route::middleware('auth:sanctum')->group( function () {
    Route::group(['prefix'=>'/dcc'],function(){
        Route::get('/',[DemandeCreationClubController::class,'show']);
        Route::get('/{id}',[DemandeCreationClubController::class,'showMyDemandes']);
        Route::post('/',[DemandeCreationClubController::class,'create']);
        Route::put('/{id}',[DemandeCreationClubController::class,'update']);
        Route::delete('/{id}',[DemandeCreationClubController::class,'delete']);
        Route::put('/a/{id}',[DemandeCreationClubController::class,'accept']);
        Route::put('/d/{id}',[DemandeCreationClubController::class,'decline']);
    });

    Route::group(['prefix'=>'/dac'],function(){
        Route::prefix('/responsable')->group(function () {
            Route::get('/',[ClubController::class,'getDemandeAdhesionByClub']);
            Route::put('/a/{id}',[ClubController::class,'acceptDemandeAdhesion']);
            Route::put('/d/{id}',[ClubController::class,'refuserDemandeAdhesion']);
        });
        Route::post('/', [ClubController::class, 'DemandeeAdhesion']);
        Route::get('/', [ClubController::class, 'show']);
        Route::get('/a', [ClubController::class, 'getDemandeAdhesion']);
        Route::delete('/{id}', [ClubController::class, 'deleteDemandeAdhesion']);
    });
    // Route::apiResource('secrets', SecretsController::class);
    Route::get('/test', [DemandeCreationClubController::class, 'test']);
    // Route::get('/dcc', [DemandeCreationClubController::class, 'get']);
});

Route::group(['prefix'=>'/Salle'],function(){
    Route::get('/{id}',[SalleController::class,'index']);
});
Route::group(['prefix'=>'/Departement'],function(){
    Route::get('/',[DepartementController::class,'index']);
});
Route::group(['prefix'=>'/DemandeSalle'],function(){
    Route::get('/',[DemandeSalleController::class,'index']);
    Route::post('/',[DemandeSalleController::class,'store']);
    Route::put('/{id}',[DemandeSalleController::class,'update']);
    Route::delete('/{id}',[DemandeSalleController::class,'destroy']);

});
