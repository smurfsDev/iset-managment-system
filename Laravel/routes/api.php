<?php

use App\Http\Controllers\DemandeCreationClubController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\DemandeMaterielController;

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
});
Route::group(['prefix'=>'/dm'],function(){
    Route::get('/',[DemandeMaterielController::class,'show']);
    // Route::get('/{id}',[DemandeMaterielController::class,'showMyDemandes']);
    Route::post('/',[DemandeMaterielController::class,'create']);
    Route::put('/{id}',[DemandeMaterielController::class,'update']);
    Route::delete('/{id}',[DemandeMaterielController::class,'delete']);
});
