<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\MaterielController;
use App\Http\Controllers\DemandeMaterielController;
use App\Http\Controllers\CategorieMaterielController;
use App\Http\Controllers\DemandeCreationClubController;

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
    Route::put('/{idM}/{idD}',[MaterielController::class,'update']);
    Route::delete('/{id}',[MaterielController::class,'delete']);


});
Route::group(['prefix'=>'/c'],function(){
    Route::get('/',[CategorieMaterielController::class,'show']);
});
