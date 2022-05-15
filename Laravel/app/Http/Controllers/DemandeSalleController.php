<?php

namespace App\Http\Controllers;

use App\Http\Requests\DemandeMaterielRequest;
use App\Models\DemandeSalle;
use App\Models\User;
use Illuminate\Http\Request;

class DemandeSalleController extends Controller
{
    public function index(Request $request){
        $demandeSalle = $request->user()->DemandeSalle()->with('salles')->paginate(5);
          if(!empty($demandeSalle)){
               return response()->json(["data"=>$demandeSalle], 200);
          }
          else
               return response()->json([
                 "aucune demande de salle"
               ], 404);
    }
    public function store(DemandeMaterielRequest $request){
        $dateEmploi = $request->input('dateEmploi');
        $dateDeRemise = $request->input('dateDeRemise');
        $idSalle = $request->input('idSalle');
        $data = array(
            "dateEmploi"=> $dateEmploi,
            "dateDeRemise"=> $dateDeRemise,
            "idResponsable"=> $request->user()->id,
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
    public function update(DemandeMaterielRequest $request, $id){
        $demandeSalle=$request->user()->DemandeSalle()->find($id);
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
    public function demandeSalleUser(Request $request){
        $demandeSalle = DemandeSalle::with("salles")->with('responsable')->whereHas("salles.departement", function ($query) use ($request) {
            $query->where('chef_departement_id', $request->user()->id);
        })->orderBy('created_at')->paginate(5);
        if(!empty($demandeSalle)){
        return response()->json($demandeSalle, 200);
        }
        else
            return response()->json([
                "aucune demande"
            ], 404);
    }
    public function accept($id){
        $demandeSalle = DemandeSalle::find($id);
        if ($demandeSalle) {
            $demandeSalle->status = 1;
            $demandeSalle->save();
            return response()->json('demande Salle accepted',200);
        } else {
            return response()->json([
                'type' => 'demandeSalle',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
    public function refuse($id){
        $demandeSalle = DemandeSalle::find($id);
        if ($demandeSalle) {
            $demandeSalle->status = 2;
            $demandeSalle->save();
            return response()->json('demande Salle refused',200);
        } else {
            return response()->json([
                'type' => 'demandeSalle',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
    public function setReponse($id,Request $request){
        $demandeSalle = DemandeSalle::find($id);
        if ($demandeSalle) {
            $demandeSalle->reponse = $request->input('reponse');;
            $demandeSalle->save();
            return response()->json('reponse set',200);
        } else {
            return response()->json([
                'type' => 'demandeSalle',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }



}
