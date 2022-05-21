<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Validation\ValidationException;
use App\Models\avis;

class posteavisController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $avis=avis::get();
        return $avis;
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {

        // $validated = $request->validate([
        //     'title' => 'required|max:255',
        //     'content' => 'required',
        // ]);

        $avis= new avis();


            
        
            $avis->title=$request->get('title');
            $avis->content=$request->get('content');
            $avis->image= $request->get('image');
            // $avis->image=$request->file('image')->hashName();
            $avis->save();
        
 
        //return redirect()->route('/listavis');

    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $data = avis::find($id);
        return $data;
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $avis = avis::find($id);
        $avis->title=$request->get('title');
        $avis->content=$request->get('content');
        $avis->content="xx";
        $avis->save();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $avis = avis::find($id);
        $avis->delete();
    }
}
