<?php

namespace App\Http\Controllers;

use App\Models\DemandeSalle;
use Illuminate\Http\Request;

class DemandeSalleController extends Controller
{
    public function index(){
        $demandeSalle=DemandeSalle::with("salles")->paginate(5);
          if(sizeof($demandeSalle)>0){
               return response()->json(["data"=>$demandeSalle], 200);
          }
          else
               return response()->json([
                 "aucune demande de salle"
               ], 404);
    }
    public function store(Request $request){
        $dateEmploi = $request->input('dateEmploi');
        $dateDeRemise = $request->input('dateDeRemise');
        $idSalle = $request->input('idSalle');
        $data = array(
            "dateEmploi"=> $dateEmploi,
            "dateDeRemise"=> $dateDeRemise,
            "idResponsable"=> "2",
            "idSalle"=> $idSalle,
        );
        $demandeSalle=DemandeSalle::create($data);
        return response()->json([
            'data' => [
                'message' => 'Success',
                'id' => $demandeSalle->id,
                'attributes' => $demandeSalle
            ]
        ], 201);
    }
    public function show($id){
        $demandeSalle=DemandeSalle::find($id);
        if(!$demandeSalle)
            return response()->json(["message"=>"aucune demande de salle"], 404);
        return response()->json(["data"=>$demandeSalle], 200);
    }
    public function update(Request $request, $id){
        $demandeSalle=DemandeSalle::find($id);
        if(!$demandeSalle)
            return response()->json(["message"=>"aucune demande de salle"], 404);
        $demandeSalle->update($request->all());
        return response()->json(["data"=>$demandeSalle], 200);
    }
    public function destroy($id){
        $demandeSalle=DemandeSalle::find($id);
        if(!$demandeSalle)
            return response()->json(["message"=>"aucune demande de salle"], 404);
        $demandeSalle->delete();
        return response()->json(["message"=>"demande de salle supprimée"], 200);
    }


}
